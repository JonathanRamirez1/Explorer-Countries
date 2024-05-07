package com.jonathan.explorercountries.data.datasource.local

import com.jonathan.explorercountries.data.datasource.local.entity.CountryEntity

interface CountryLocalDatasource {
    suspend fun insertAll(countries: List<CountryEntity?>)
    suspend fun getAllCountries(): List<CountryEntity>
    suspend fun findCountryByName(name: String): List<CountryEntity>
    suspend fun deleteAllCountries()
}
