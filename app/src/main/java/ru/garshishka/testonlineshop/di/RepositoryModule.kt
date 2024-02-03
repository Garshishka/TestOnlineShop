package ru.garshishka.testonlineshop.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.garshishka.testonlineshop.repository.CatalogueRepository
import ru.garshishka.testonlineshop.repository.CatalogueRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindsFoodRepository(
        foodRepositoryImpl: CatalogueRepositoryImpl
    ): CatalogueRepository
}