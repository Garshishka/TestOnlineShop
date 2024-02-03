package ru.garshishka.testonlineshop.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.garshishka.testonlineshop.dto.Info

class Converters {
    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        if (value == null) {
            return emptyList()
        }
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toStringList(list: List<String>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromInfoList(value: String?): List<Info>? {
        if (value == null) {
            return emptyList()
        }
        val type = object : TypeToken<List<Info>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toInfoList(list: List<Info>?): String {
        return Gson().toJson(list)
    }
}