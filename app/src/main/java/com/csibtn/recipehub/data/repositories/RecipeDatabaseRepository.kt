package com.csibtn.recipehub.data.repositories

import androidx.room.Room
import com.csibtn.recipehub.data.RecipeDatabase
import com.csibtn.recipehub.data.model.Recipe
import com.csibtn.recipehub.util.Constants
import com.csibtn.recipehub.util.RecipeApplication
import kotlinx.coroutines.flow.Flow

object RecipeDatabaseRepository : RecipeDatabaseRepositoryInterface {

    private val recipeDatabase: RecipeDatabase

    init {
        recipeDatabase = Room.databaseBuilder(
            RecipeApplication.applicationContext().applicationContext,
            RecipeDatabase::class.java,
            Constants.databaseName
        ).build()
    }


    override suspend fun removeRecipe(recipe: Recipe) =
        recipeDatabase.recipeDao().deleteRecipe(recipe)

    override suspend fun getRecipeById(id: Int) = recipeDatabase.recipeDao().getRecipesById(id)

    override suspend fun getBookmarkedRecipes(): Flow<List<Recipe>> =
        recipeDatabase.recipeDao().getRecipes()

    override suspend fun addRecipe(recipe: Recipe) = recipeDatabase.recipeDao().insertRecipe(recipe)

    suspend fun deleteAll() = recipeDatabase.recipeDao().deleteAll()
}