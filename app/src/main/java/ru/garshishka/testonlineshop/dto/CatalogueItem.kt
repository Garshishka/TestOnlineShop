package ru.garshishka.testonlineshop.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatalogueItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("subtitle")
    val subtitle : String,
    @SerializedName("price")
    val price: Price,
    @SerializedName("feedback")
    val feedback: Feedback?,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("available")
    val available: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("info")
    val info: List<Info>,
    @SerializedName("ingredients")
    val ingredients: String,
    val favorite: Boolean = false,
) :Parcelable

@Parcelize
data class Price(
    @SerializedName("price")
    val price: Int,
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("priceWithDiscount")
    val priceWithDiscount: Int,
    @SerializedName("unit")
    val unit: String,
) :Parcelable

@Parcelize
data class Feedback(
    @SerializedName("count")
    val count: Int,
    @SerializedName("rating")
    val rating: Double,
):Parcelable

@Parcelize
data class Info(
    @SerializedName("title")
    val title: String,
    @SerializedName("value")
    val value: String,
):Parcelable

@Parcelize
data class Items(
    @SerializedName("items")
    val items: List<CatalogueItem>,
):Parcelable