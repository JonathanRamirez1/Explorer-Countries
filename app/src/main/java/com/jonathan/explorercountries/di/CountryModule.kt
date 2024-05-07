package com.jonathan.explorercountries.di

import com.jonathan.explorercountries.data.datasource.local.CountryLocalDatasource
import com.jonathan.explorercountries.data.datasource.local.CountryLocalDatasourceImpl
import com.jonathan.explorercountries.data.datasource.remote.network.CountryRemoteDatasource
import com.jonathan.explorercountries.data.datasource.remote.network.CountryRemoteDatasourceImpl
import com.jonathan.explorercountries.domain.repository.CountryRepository
import com.jonathan.explorercountries.domain.repository.CountryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(countryRemoteDatasourceImpl: CountryRemoteDatasourceImpl): CountryRemoteDatasource =
        countryRemoteDatasourceImpl

    @Singleton
    @Provides
    fun provideLocalDataSource(countryLocalDatasourceImpl: CountryLocalDatasourceImpl): CountryLocalDatasource =
        countryLocalDatasourceImpl

    @Singleton
    @Provides
    fun provideRepository(countryRepositoryImpl: CountryRepositoryImpl): CountryRepository =
        countryRepositoryImpl
}
