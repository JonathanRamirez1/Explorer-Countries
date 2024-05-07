package com.jonathan.explorercountries.domain.repository

import com.jonathan.explorercountries.domain.model.Country
import com.jonathan.explorercountries.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getAllCountries(): Flow<Resource<List<Country>>>
    suspend fun shouldFetchFromRemote(): Boolean
    suspend fun fetchAndStoreCountries()
}
