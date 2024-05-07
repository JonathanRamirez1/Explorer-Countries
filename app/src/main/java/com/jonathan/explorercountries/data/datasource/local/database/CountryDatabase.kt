package com.jonathan.explorercountries.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jonathan.explorercountries.data.datasource.local.dao.CountryDao
import com.jonathan.explorercountries.data.datasource.local.entity.CountryEntity
import com.jonathan.explorercountries.presentation.utils.Converters

@Database(entities = [CountryEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CountryDatabase: RoomDatabase() {

    abstract fun countryDao(): CountryDao
}
