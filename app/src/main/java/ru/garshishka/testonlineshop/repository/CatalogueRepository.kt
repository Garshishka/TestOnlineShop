package ru.garshishka.testonlineshop.repository

import androidx.lifecycle.LiveData
import ru.garshishka.testonlineshop.dto.CatalogueItem
import ru.garshishka.testonlineshop.utils.SortingCriteria

interface CatalogueRepository {
    val catalogueItems: LiveData<List<CatalogueItem>>
    suspend fun downloadAll()
    suspend fun sortCatalogue(sortingCriteria: SortingCriteria)
}