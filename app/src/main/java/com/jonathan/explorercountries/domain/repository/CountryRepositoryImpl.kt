package com.jonathan.explorercountries.domain.repository

import android.content.SharedPreferences
import com.jonathan.explorercountries.data.datasource.local.CountryLocalDatasource
import com.jonathan.explorercountries.data.datasource.local.entity.CountryEntity
import com.jonathan.explorercountries.data.datasource.remote.model.CountryModel
import com.jonathan.explorercountries.data.datasource.remote.model.FlagsModel
import com.jonathan.explorercountries.data.datasource.remote.model.NameModel
import com.jonathan.explorercountries.data.datasource.remote.network.CountryRemoteDatasource
import com.jonathan.explorercountries.di.IoDispatcher
import com.jonathan.explorercountries.domain.model.Country
import com.jonathan.explorercountries.presentation.utils.InternetCheck.isNetworkAvailable
import com.jonathan.explorercountries.presentation.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val remoteDatasource: CountryRemoteDatasource,
    private val localDatasource: CountryLocalDatasource,
    private val sharedPreferences: SharedPreferences,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CountryRepository {

    override fun getAllCountries(): Flow<Resource<List<Country>>> = flow {
        emit(Resource.Loading())
        if (shouldFetchFromRemote()) {
            try {
                fetchAndStoreCountries()
            } catch (ioException: IOException) {
                emit(Resource.Error("Unable to load data from remote source", null))
            }
        }
        val localCountries = localDatasource.getAllCountries()
        emit(Resource.Success(localCountries.map { it.toDomainModel() }))
    }.flowOn(ioDispatcher)

    override suspend fun shouldFetchFromRemote(): Boolean {
        val dataLoaded = sharedPreferences.getBoolean("DATA_LOADED", false)
        return isNetworkAvailable() && !dataLoaded
    }

    override suspend fun fetchAndStoreCountries() {
        val remoteCountries = remoteDatasource.getAllCountries()
        val countriesToStore = remoteCountries.map { it?.toEntity() }
        localDatasource.deleteAllCountries()
        localDatasource.insertAll(countriesToStore)
        sharedPreferences.edit().putBoolean("DATA_LOADED", true).apply()
    }

    private fun CountryModel.toEntity(): CountryEntity {
        return CountryEntity(
            officialName = this.name?.official ?: "Unknown",
            capital = this.capital?.joinToString(separator = ", ") ?: "No Capital",
            flagPngUrl = this.flags?.png ?: "",
            region = this.region ?: "Unknown",
            subregion = this.subregion ?: "Unknown",
            population = this.population ?: 0,
            area = this.area ?: 0.0,
            borders = this.borders?.joinToString(separator = ", ") ?: "No Borders",
            languages = this.languages?.map { "${it.key}: ${it.value}" }?.joinToString(separator = ", ") ?: "No Languages",
            currencies = this.currencies?.entries?.joinToString(separator = ", ") {
                "${it.value.name} (${it.value.symbol})"
            } ?: "No Currencies"
        )
    }

    private fun CountryEntity.toDomainModel(): Country {
        return Country(
            name = NameModel(official = this.officialName),
            capital = this.capital?.split(", ") ?: listOf(),
            flags = FlagsModel(png = this.flagPngUrl),
            region = this.region,
            subregion = this.subregion,
            population = this.population,
            area = this.area,
            borders = this.borders,
            languages = this.languages,
            currencies = this.currencies
        )
    }
}
