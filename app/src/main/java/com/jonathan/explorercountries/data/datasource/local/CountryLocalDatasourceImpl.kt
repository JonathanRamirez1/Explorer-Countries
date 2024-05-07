package com.jonathan.explorercountries.data.datasource.local

import com.jonathan.explorercountries.data.datasource.local.dao.CountryDao
import com.jonathan.explorercountries.data.datasource.local.entity.CountryEntity
import javax.inject.Inject

class CountryLocalDatasourceImpl @Inject constructor(private val countryDao: CountryDao): CountryLocalDatasource {

    override suspend fun insertAll(countries: List<CountryEntity?>) {
        countryDao.insertAll(countries)
    }

    override suspend fun getAllCountries(): List<CountryEntity> {
        return countryDao.getAllCountries()
    }

    override suspend fun findCountryByName(name: String): List<CountryEntity> {
        return countryDao.findCountryByName(name)
    }

    override suspend fun deleteAllCountries() {
        countryDao.deleteAllCountries()
    }
}
