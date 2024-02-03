package ru.garshishka.testonlineshop.utils

import ru.garshishka.testonlineshop.dto.CatalogueItem

enum class SortingCriteria {
    POPULARITY, PRICE_DOWN, PRICE_UP
}

fun getComparator( sortingCriteria: SortingCriteria) : Comparator<CatalogueItem>{
    return when (sortingCriteria){
        SortingCriteria.POPULARITY -> compareByDescending { it.feedback?.rating }
        SortingCriteria.PRICE_DOWN -> compareByDescending { it.price.price }
        SortingCriteria.PRICE_UP -> compareBy { it.price.price }
    }
}