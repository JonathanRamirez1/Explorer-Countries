package com.jonathan.explorercountries.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jonathan.explorercountries.domain.model.Country
import com.jonathan.explorercountries.domain.repository.CountryRepository
import com.jonathan.explorercountries.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) :
    ViewModel() {

    private val countriesFlow = countryRepository.getAllCountries().asLiveData(viewModelScope.coroutineContext)

    private val searchQuery = MutableLiveData("")

    val countries: LiveData<Resource<List<Country>>> = Transformations.switchMap(searchQuery) { query ->
        Transformations.map(countriesFlow) { resource ->
            when(resource) {
                is Resource.Success -> {
                    Resource.Success(filterCountries(resource.data, query))
                }
                else -> resource
            }
        }
    }

    private fun filterCountries(countries: List<Country>, query: String): List<Country> {
        if (query.isEmpty()) {
            return countries
        }
        return countries.filter { country ->
            country.name?.official?.contains(query, ignoreCase = true) == true
        }
    }

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun refreshCountries() {
        viewModelScope.launch {
            if (countryRepository.shouldFetchFromRemote()) {
                countryRepository.fetchAndStoreCountries()
            }
        }
    }
}
