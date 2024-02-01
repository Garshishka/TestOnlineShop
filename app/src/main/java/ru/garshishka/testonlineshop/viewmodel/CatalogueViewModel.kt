package ru.garshishka.testonlineshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import ru.garshishka.testonlineshop.dto.CatalogueItem
import ru.garshishka.testonlineshop.dto.Items

class CatalogueViewModel : ViewModel() {

    private val _catalogueItems = MutableLiveData<List<CatalogueItem>>()
    val catalogueItems : LiveData<List<CatalogueItem>>
        get() = _catalogueItems
    fun parseJson(jsonString: String){
        try {
            val gson = Gson()
            val yourDataObject = gson.fromJson(jsonString, Items::class.java)

            _catalogueItems.value = yourDataObject.items
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}

