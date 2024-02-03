package ru.garshishka.testonlineshop.utils.enums

import ru.garshishka.testonlineshop.dto.CatalogueItem

enum class SortType {
    POPULARITY, PRICE_DOWN, PRICE_UP
}

fun getComparator(sortType: SortType) : Comparator<CatalogueItem>{
    return when (sortType){
        SortType.POPULARITY -> compareByDescending { it.feedback?.rating }
        SortType.PRICE_DOWN -> compareByDescending { it.price.price }
        SortType.PRICE_UP -> compareBy { it.price.price }
    }
}