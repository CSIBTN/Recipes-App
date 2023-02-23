package com.csibtn.recipehub.data.repositories

import com.csibtn.recipehub.data.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeDatabaseRepositoryInterface {
    suspend fun getRecipeById(id: Int): Recipe
    suspend fun getBookmarkedRecipes(): Flow<List<Recipe>>
    suspend fun addRecipe(recipe: Recipe)
    suspend fun removeRecipe(recipe: Recipe)
}