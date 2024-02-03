package ru.garshishka.testonlineshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.garshishka.testonlineshop.dto.CatalogueItem
import ru.garshishka.testonlineshop.repository.CatalogueRepository
import ru.garshishka.testonlineshop.utils.SortingCriteria
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val repository: CatalogueRepository,
): ViewModel() {
    val catalogueItems : LiveData<List<CatalogueItem>>
        get() = repository.catalogueItems

    init {
        load()
    }

    private fun load() = viewModelScope.launch {
        try {
            repository.downloadAll()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun changeSort(sortingCriteria: SortingCriteria) = viewModelScope.launch{
        repository.sortCatalogue(sortingCriteria)
    }

}

