package ru.garshishka.testonlineshop.utils

import ru.garshishka.testonlineshop.dto.CatalogueItem

interface UserInteractionListener {
    fun onFavoriteClick(id: String)
    fun onCardClick(item:CatalogueItem)
}