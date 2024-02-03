package ru.garshishka.testonlineshop.repository

import androidx.lifecycle.LiveData
import ru.garshishka.testonlineshop.dto.CatalogueItem

interface CatalogueRepository {
    val foodData: LiveData<List<CatalogueItem>>
    suspend fun downloadAll()
}