package com.csibtn.recipehub.data

import retrofit2.http.GET
import retrofit2.http.Query


interface RecipeApi {
    @GET(
        "recipes/complexSearch?apiKey=${api_key}")
                suspend fun fetchRecipes(@Query("query") value: String): RecipeResponse
}