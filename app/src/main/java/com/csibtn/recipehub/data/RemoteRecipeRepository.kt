package com.csibtn.recipehub.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object RemoteRecipeRepository {
    private val recipeApi: RecipeApi

    init {
        val retrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        recipeApi = retrofit.create()
    }
    suspend fun getRecipesWithTitle(title : String) : List<RecipePreview> = recipeApi.fetchRecipes(title).recipePreviews
}