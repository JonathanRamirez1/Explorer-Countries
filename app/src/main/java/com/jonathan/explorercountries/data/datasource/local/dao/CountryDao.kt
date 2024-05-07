package com.jonathan.explorercountries.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jonathan.explorercountries.data.datasource.local.entity.CountryEntity

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<CountryEntity?>)

    @Query("SELECT * FROM countries")
    suspend fun getAllCountries(): List<CountryEntity>

    @Query("SELECT * FROM countries WHERE official_name LIKE :name")
    suspend fun findCountryByName(name: String): List<CountryEntity>

    @Query("DELETE FROM countries")
    suspend fun deleteAllCountries()
}
