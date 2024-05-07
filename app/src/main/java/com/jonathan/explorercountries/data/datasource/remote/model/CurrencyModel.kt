package com.jonathan.explorercountries.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String
)
