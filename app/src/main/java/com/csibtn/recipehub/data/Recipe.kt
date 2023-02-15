package com.csibtn.recipehub.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
@JsonClass(generateAdapter = true)
data class Recipe(
    @PrimaryKey @Json(name="id") val recipeID: Int,
    @Json(name = "title") val name: String,
    val image: String,
    val healthScore: Double = 0.0,
    @Json(name = "aggregateLikes") val likes: Int = 0,
    @Json(name = "readyInMinutes") val timeToPrepare: Int,
    val sourceUrl: String,
    val summary: String
) : Parcelable