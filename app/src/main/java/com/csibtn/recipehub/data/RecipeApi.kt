package com.csibtn.recipehub.data

import com.csibtn.recipehub.BuildConfig
import com.csibtn.recipehub.data.model.Recipe
import com.csibtn.recipehub.data.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {
    @GET(
        "recipes/complexSearch?apiKey=${BuildConfig.API_KEY}"
    )
    suspend fun fetchRecipes(@Query("query") value: String): RecipeResponse

    @GET("recipes/{id}/information?apiKey=${BuildConfig.API_KEY}")
    suspend fun fetchRecipeById(@Path("id") id: Int): Recipe
    
}