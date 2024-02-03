package ru.garshishka.testonlineshop.repository

import androidx.lifecycle.LiveData
import ru.garshishka.testonlineshop.dto.CatalogueItem
import ru.garshishka.testonlineshop.utils.enums.FilterType
import ru.garshishka.testonlineshop.utils.enums.SortType

interface CatalogueRepository {
    val catalogueItems: LiveData<List<CatalogueItem>>
    suspend fun downloadAll()
    suspend fun sortCatalogue(sortType: SortType)
    suspend fun filterCatalogue(filterType: FilterType)
    suspend fun favorite(id: String)
}