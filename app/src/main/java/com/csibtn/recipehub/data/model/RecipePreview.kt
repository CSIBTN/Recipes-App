package com.csibtn.recipehub.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipePreview(
    @Json(name = "id") val recipeId : Int,
    @Json(name = "title") val name : String,
    @Json(name = "image") val imageURL: String
)