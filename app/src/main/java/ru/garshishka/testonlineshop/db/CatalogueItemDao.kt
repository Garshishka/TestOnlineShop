package ru.garshishka.testonlineshop.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CatalogueItemDao {
    @Query("SELECT * FROM catalogue_list")
    fun getAll(): LiveData<List<CatalogueItemEntity>>

    @Query("DELETE FROM catalogue_list")
    suspend fun clearTable()

    @Upsert
    suspend fun saveAll(list: List<CatalogueItemEntity>)
}