package com.jonathan.explorercountries.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class NameModel(@SerializedName("official") val official: String? = null)
