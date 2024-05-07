package com.jonathan.explorercountries.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("name") val name: NameModel? = null,
    @SerializedName("capital") val capital: List<String>? = null,
    @SerializedName("flags") val flags: FlagsModel? = null,
    @SerializedName("region") val region: String? = null,
    @SerializedName("subregion") val subregion: String? = null,
    @SerializedName("population") val population: Long? = null,
    @SerializedName("area") val area: Double? = null,
    @SerializedName("borders") val borders: List<String>? = null,
    @SerializedName("languages") val languages: Map<String, String>? = null,
    @SerializedName("currencies") val currencies: Map<String, CurrencyModel>? = null
)
