package com.jonathan.explorercountries.di

import android.content.Context
import androidx.room.Room
import com.jonathan.explorercountries.data.datasource.local.database.CountryDatabase
import com.jonathan.explorercountries.presentation.utils.Constants.COUNTRY_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CountryDatabase::class.java, COUNTRY_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideItemsDatabase(countryDatabase: CountryDatabase) = countryDatabase.countryDao()
}