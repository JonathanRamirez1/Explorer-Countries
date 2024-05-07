package com.jonathan.explorercountries.data.datasource.remote.network

import com.jonathan.explorercountries.data.datasource.remote.model.CountryModel
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {

    @GET("all")
    suspend fun getAllCountries(): Response<List<CountryModel>>
}