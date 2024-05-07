package com.jonathan.explorercountries.data.datasource.remote.network

import com.jonathan.explorercountries.data.datasource.remote.model.CountryModel
import com.jonathan.explorercountries.data.datasource.remote.model.FlagsModel
import com.jonathan.explorercountries.data.datasource.remote.model.NameModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CountryRemoteDatasourceImplTest {

    private lateinit var countryApi: CountryApi
    private lateinit var remoteDataSource: CountryRemoteDatasource

    @Before
    fun setUp() {
        countryApi = mockk()
        remoteDataSource = CountryRemoteDatasourceImpl(countryApi)
    }

    @Test
    fun getAllCountries_successfulResponse_returnsCountries() = runBlocking {
        val mockCountries = listOf(CountryModel(NameModel("Country"), listOf("Capital"), FlagsModel("url"), "Region", "Subregion", 1000L, 100.0, listOf("Borders"), null, null))
        val response = Response.success(mockCountries)

        coEvery { countryApi.getAllCountries() } returns response

        val result = remoteDataSource.getAllCountries()

        assertEquals(mockCountries, result)
    }
}
