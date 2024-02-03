package ru.garshishka.testonlineshop.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.garshishka.testonlineshop.db.CatalogueItemDao
import ru.garshishka.testonlineshop.db.CatalogueItemEntity
import ru.garshishka.testonlineshop.dto.CatalogueItem
import ru.garshishka.testonlineshop.dto.Items
import ru.garshishka.testonlineshop.utils.enums.FilterType
import ru.garshishka.testonlineshop.utils.enums.SortType
import ru.garshishka.testonlineshop.utils.enums.getComparator
import ru.garshishka.testonlineshop.utils.enums.getTag
import java.io.InputStreamReader
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogueRepositoryImpl @Inject constructor(
    private val catalogueItemDao: CatalogueItemDao
) : CatalogueRepository {
    private var catalogueSort: SortType? = null

    private val _catalogueItems: MutableLiveData<List<CatalogueItem>> = getSortedItems()
    override val catalogueItems: LiveData<List<CatalogueItem>>
        get() = _catalogueItems

    private fun getSortedItems(): MutableLiveData<List<CatalogueItem>> {

        val result = MediatorLiveData<List<CatalogueItem>>()

        val sourceLiveData = catalogueItemDao.getAllLiveData()
        result.addSource(sourceLiveData) { itemList ->
            val sortedList = entityListToSortedList(itemList)
            result.value = sortedList
        }

        return result
    }

    override suspend fun downloadAll()  {
        val url = "https://run.mocky.io/v3/97e721a7-0a66-4cae-b445-83cc0bcf9010"

        val jsonString = readUrl(url)

        val catalogue = Gson().fromJson(jsonString, Items::class.java).items

        catalogueItemDao.saveAll(catalogue.map(CatalogueItemEntity.Companion::fromDto))
    }

    override suspend fun sortCatalogue(sortType: SortType) = withContext(Dispatchers.IO) {
        catalogueSort = sortType
        val newSort =
            _catalogueItems.value?.sortedWith(getComparator(sortType))
                ?: listOf()
        _catalogueItems.postValue(newSort)
    }

    override suspend fun filterCatalogue(filterType: FilterType) = withContext(Dispatchers.IO) {
        val filterTag = filterType.getTag()
        var catalogue: List<CatalogueItem> = entityListToSortedList(catalogueItemDao.getAll())

        filterTag?.let { tag ->
            catalogue = catalogue.filter { it.tags.contains(tag) }
        }
        _catalogueItems.postValue(catalogue)
    }


    private suspend fun readUrl(url: String): String = withContext(Dispatchers.IO) {
        val connection = URL(url).openConnection()
        connection.connectTimeout = 1000
        connection.readTimeout = 1000

        try {
            InputStreamReader(connection.getInputStream()).use { reader ->
                val charArray = CharArray(8192)
                val builder = StringBuilder()
                var bytesRead: Int
                while (reader.read(charArray, 0, charArray.size).also { bytesRead = it } != -1) {
                    builder.appendRange(charArray, 0, bytesRead)
                }
                return@withContext builder.toString()
            }
        } finally {
            connection.getInputStream().close()
        }
    }

    private fun entityListToSortedList(list: List<CatalogueItemEntity>): List<CatalogueItem> =
        list.map(CatalogueItemEntity::toDto)
            .run { if (catalogueSort != null) sortedWith(getComparator(catalogueSort!!)) else this }

}