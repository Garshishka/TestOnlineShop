package ru.garshishka.testonlineshop.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.garshishka.testonlineshop.dto.CatalogueItem
import ru.garshishka.testonlineshop.dto.Feedback
import ru.garshishka.testonlineshop.dto.Info
import ru.garshishka.testonlineshop.dto.Price

@Entity(tableName = "catalogue_list")
data class CatalogueItemEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val subtitle: String,
    val price: Int,
    val discount: Int,
    val priceWithDiscount: Int,
    val priceUnit: String,
    val feedbackCount: Int?,
    val feedbackRating: Double?,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info: List<Info>,
    val ingredients: String,
) {
    fun toDto() = CatalogueItem(
        id,
        title,
        subtitle,
        Price(price, discount, priceWithDiscount, priceUnit),
        feedbackCount?.let { Feedback(feedbackCount, feedbackRating!!) },
        tags,
        available,
        description,
        info,
        ingredients
    )

    companion object {
        fun fromDto(dto: CatalogueItem) =
            CatalogueItemEntity(
                dto.id,
                dto.title,
                dto.subtitle,
                dto.price.price,
                dto.price.discount,
                dto.price.priceWithDiscount,
                dto.price.unit,
                dto.feedback?.count,
                dto.feedback?.rating,
                dto.tags,
                dto.available,
                dto.description,
                dto.info,
                dto.ingredients
            )
    }
}