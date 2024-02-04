package ru.garshishka.testonlineshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.garshishka.testonlineshop.dto.CatalogueItem
import ru.garshishka.testonlineshop.repository.CatalogueRepository
import ru.garshishka.testonlineshop.utils.enums.FilterType
import ru.garshishka.testonlineshop.utils.enums.SortType
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val repository: CatalogueRepository,
) : ViewModel() {
    val catalogueItems: LiveData<List<CatalogueItem>>
        get() = repository.catalogueItems

    private val _favoritesAmount = MutableLiveData<Int>()
    val favoritesAmount: LiveData<Int>
        get() = _favoritesAmount


    init {
        load()
    }

    private fun load() = viewModelScope.launch {
        try {
            repository.downloadAll()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun changeSort(sortType: SortType) = viewModelScope.launch {
        repository.sortCatalogue(sortType)
    }

    fun changeFilter(filterType: FilterType) = viewModelScope.launch {
        repository.filterCatalogue(filterType)
    }

    fun favorite(id: String) = viewModelScope.launch {
        try {
            repository.favorite(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getFavoritesAmount() = viewModelScope.launch {
        _favoritesAmount.postValue(repository.getAllFavorite())
    }

    fun clearFavorites() = viewModelScope.launch {
        repository.clearFavorites()
    }
}

