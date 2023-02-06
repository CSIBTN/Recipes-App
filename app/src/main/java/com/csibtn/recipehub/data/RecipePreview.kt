package com.csibtn.recipehub.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipePreview(
    @Json(name = "title") val name : String,
    @Json(name = "image") val imageURL: String
)