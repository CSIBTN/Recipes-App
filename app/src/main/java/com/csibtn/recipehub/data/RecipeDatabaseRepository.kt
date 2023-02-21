package com.csibtn.recipehub.data

import androidx.room.Room
import com.csibtn.recipehub.util.Constants
import com.csibtn.recipehub.util.RecipeApplication

object RecipeDatabaseRepository {
    private val recipeDatabase: RecipeDatabase =
        Room.databaseBuilder(
            RecipeApplication.applicationContext().applicationContext,
            RecipeDatabase::class.java,
            Constants.databaseName
        ).build()

    suspend fun getRecipeById(id: Int) = recipeDatabase.recipeDao().getRecipesById(id)
    suspend fun getBookmarkedRecipes(): List<Recipe> = recipeDatabase.recipeDao().getRecipes()
    suspend fun addRecipe(recipe: Recipe) = recipeDatabase.recipeDao().insertRecipe(recipe)
}