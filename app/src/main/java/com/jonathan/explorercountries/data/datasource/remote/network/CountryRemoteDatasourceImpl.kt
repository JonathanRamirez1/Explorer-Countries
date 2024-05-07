package com.jonathan.explorercountries.data.datasource.remote.network

import com.jonathan.explorercountries.data.datasource.remote.model.CountryModel
import javax.inject.Inject

class CountryRemoteDatasourceImpl @Inject constructor(private val countryApi: CountryApi): CountryRemoteDatasource {

    override suspend fun getAllCountries(): List<CountryModel?> {
        val response = countryApi.getAllCountries()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Failed to fetch countries: ${response.errorBody()?.string()}")
        }
    }
}
