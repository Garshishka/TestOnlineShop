package ru.garshishka.testonlineshop.utils.enums

import ru.garshishka.testonlineshop.R

enum class FilterType {
    ALL, FACE, BODY, SUNTAN, MASK
}

fun FilterType.getTag() : String? = when(this){
    FilterType.ALL -> null
    FilterType.FACE -> "face"
    FilterType.BODY -> "body"
    FilterType.SUNTAN -> "suntan"
    FilterType.MASK -> "mask"
}

fun getFilterType(id: Int) : FilterType = when(id){
    R.id.chip_body -> FilterType.BODY
    R.id.chip_face -> FilterType.FACE
    R.id.chip_suntan -> FilterType.SUNTAN
    R.id.chip_mask -> FilterType.MASK
    else -> FilterType.ALL
}