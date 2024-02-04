package ru.garshishka.testonlineshop.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CatalogueItemDao {
    @Query("SELECT * FROM catalogue_list")
    fun getAllLiveData(): LiveData<List<CatalogueItemEntity>>

    @Query("SELECT * FROM catalogue_list")
    fun getAll(): List<CatalogueItemEntity>

    @Insert
    suspend fun saveAll(list: List<CatalogueItemEntity>)

    @Update
    suspend fun updateItem(itemEntity: CatalogueItemEntity)

    @Query("SELECT * FROM catalogue_list WHERE id = :entityId")
    fun getEntityById(entityId: String): CatalogueItemEntity?

    @Query("SELECT * FROM catalogue_list WHERE favorite = 1")
    fun getAllFavorite(): List<CatalogueItemEntity>

    @Query("SELECT * FROM catalogue_list WHERE favorite = 1")
    fun getAllFavoriteLiveData(): LiveData<List<CatalogueItemEntity>>
}