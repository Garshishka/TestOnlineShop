package ru.garshishka.testonlineshop.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_list")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val surname: String,
    val phone: String,
    val favorites: List<String>
) {
}