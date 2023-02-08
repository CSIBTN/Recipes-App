package com.csibtn.recipehub.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Recipe(
    @PrimaryKey val recipeID: Int,
    @Json(name = "title") val name: String,
    val image: String,
    val healthScore: Double = 0.0,
    @Json(name = "aggregateLikes") val likes: Int = 0,
    @Json(name = "readyInMinutes") val timeToPrepare: Int,
    val sourceUrl: String,
    val summary: String
) {}