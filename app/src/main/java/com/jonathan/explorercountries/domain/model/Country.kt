package com.jonathan.explorercountries.domain.model

import com.jonathan.explorercountries.data.datasource.remote.model.FlagsModel
import com.jonathan.explorercountries.data.datasource.remote.model.NameModel

data class Country(
    val name: NameModel? = null,
    val capital: List<String>? = null,
    val flags: FlagsModel? = null,
    val region: String? = null,
    val subregion: String? = null,
    val population: Long? = null,
    val area: Double? = null,
    val borders: String? = null,
    val languages: String? = null,
    val currencies: String? = null
)
