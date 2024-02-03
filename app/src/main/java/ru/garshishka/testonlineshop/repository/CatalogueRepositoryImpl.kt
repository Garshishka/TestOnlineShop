package ru.garshishka.testonlineshop.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.garshishka.testonlineshop.db.CatalogueItemDao
import ru.garshishka.testonlineshop.db.CatalogueItemEntity
import ru.garshishka.testonlineshop.dto.CatalogueItem
import ru.garshishka.testonlineshop.dto.Items
import java.io.InputStreamReader
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogueRepositoryImpl @Inject constructor(
    private val catalogueItemDao: CatalogueItemDao,
) : CatalogueRepository {
    override val foodData: LiveData<List<CatalogueItem>> = catalogueItemDao.getAll().map {
        it.map(CatalogueItemEntity::toDto)
    }

    override suspend fun downloadAll() {
        val url = "https://run.mocky.io/v3/97e721a7-0a66-4cae-b445-83cc0bcf9010"

        val jsonString = readUrl(url)

        val catalogue = Gson().fromJson(jsonString, Items::class.java).items

        catalogueItemDao.saveAll(catalogue.map(CatalogueItemEntity.Companion::fromDto))
    }

    private suspend fun readUrl(url: String): String  = withContext(Dispatchers.IO){
        val connection = URL(url).openConnection()
        connection.connectTimeout = 1000 // Set the connection timeout if needed
        connection.readTimeout = 1000 // Set the read timeout if needed

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
//            connection.getInputStream().close()
        }
    }
}