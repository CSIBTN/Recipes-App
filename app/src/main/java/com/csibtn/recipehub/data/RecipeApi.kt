package com.csibtn.recipehub.data

import com.csibtn.recipehub.data.model.Recipe
import com.csibtn.recipehub.data.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val api_key = "d3ed4c6e54b745c98e16b7f9d3fe4b0b"

interface RecipeApi {
    @GET(
        "recipes/complexSearch?apiKey=${api_key}"
    )
    suspend fun fetchRecipes(@Query("query") value: String): RecipeResponse

    @GET("recipes/{id}/information?apiKey=${api_key}")
    suspend fun fetchRecipeById(@Path("id") id : Int) : Recipe
}