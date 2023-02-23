package com.csibtn.recipehub.data.repositories

import com.csibtn.recipehub.data.RecipeApi
import com.csibtn.recipehub.data.model.Recipe
import com.csibtn.recipehub.data.model.RecipePreview
import com.csibtn.recipehub.util.Resource
import com.csibtn.recipehub.util.Status
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object RemoteRecipeRepository : RemoteRecipeRepositoryInterface {
    private val recipeApi: RecipeApi

    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(MoshiConverterFactory.create()).build()
        recipeApi = retrofit.create()
    }

    override suspend fun getRecipesWithTitle(title: String): Resource<List<RecipePreview>> = try {
        val recipes = recipeApi.fetchRecipes(title).recipePreviews
        Resource(Status.SUCCESS, recipes, "SUCCESS")
    } catch (e: Exception) {
        Resource(
            Status.ERROR, emptyList(), "Failed to retrieve recipes, check your Internet connection"
        )
    }

    override suspend fun getRecipeById(id: Int): Resource<Recipe?> = try {
        val retrievedRecipe = recipeApi.fetchRecipeById(id)
        Resource(Status.SUCCESS, retrievedRecipe, "SUCCESS")
    } catch (e: Exception) {
        Resource(
            Status.ERROR, null, "Failed to retrieve recipes, check your Internet connection"
        )
    }
}
