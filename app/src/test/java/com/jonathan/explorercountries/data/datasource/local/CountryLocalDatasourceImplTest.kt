package com.jonathan.explorercountries.data.datasource.local

import com.jonathan.explorercountries.data.datasource.local.dao.CountryDao
import com.jonathan.explorercountries.data.datasource.local.entity.CountryEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountryLocalDataSourceImplTest {

    @Mock
    private lateinit var countryDao: CountryDao
    private lateinit var localDataSource: CountryLocalDatasource

    @Before
    fun setUp() {
        localDataSource = CountryLocalDatasourceImpl(countryDao)
    }

    @Test
    fun insertAll_invokesCorrectDaoMethod() = runBlocking {
        val countries = listOf(CountryEntity(1, "CountryName", "Capital", "FlagUrl", "Region", "Subregion", 1000, 100.0, "Borders", "Languages", "Currencies"))
        localDataSource.insertAll(countries)
        verify(countryDao).insertAll(countries)
    }

    @Test
    fun getAllCountries_returnsCorrectData() = runBlocking {
        val expectedCountries = listOf(CountryEntity(1, "CountryName", "Capital", "FlagUrl", "Region", "Subregion", 1000, 100.0, "Borders", "Languages", "Currencies"))
        `when`(countryDao.getAllCountries()).thenReturn(expectedCountries)
        val actualCountries = localDataSource.getAllCountries()
        assertEquals(expectedCountries, actualCountries)
    }

    @Test
    fun deleteAllCountries_invokesCorrectDaoMethod() = runBlocking {
        localDataSource.deleteAllCountries()
        verify(countryDao).deleteAllCountries()
    }
}
