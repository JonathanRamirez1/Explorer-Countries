package com.jonathan.explorercountries.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "official_name") val officialName: String,
    @ColumnInfo(name = "capital") val capital: String?,
    @ColumnInfo(name = "flag_png_url") val flagPngUrl: String,
    @ColumnInfo(name = "region") val region: String?,
    @ColumnInfo(name = "subregion") val subregion: String?,
    @ColumnInfo(name = "population") val population: Long,
    @ColumnInfo(name = "area") val area: Double,
    @ColumnInfo(name = "borders") val borders: String?,
    @ColumnInfo(name = "languages") val languages: String?,
    @ColumnInfo(name = "currencies") val currencies: String?
)
