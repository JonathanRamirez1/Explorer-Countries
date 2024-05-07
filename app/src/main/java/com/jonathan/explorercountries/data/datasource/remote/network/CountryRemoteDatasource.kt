package com.jonathan.explorercountries.data.datasource.remote.network

import com.jonathan.explorercountries.data.datasource.remote.model.CountryModel

interface CountryRemoteDatasource {

    suspend fun getAllCountries(): List<CountryModel?>
}
