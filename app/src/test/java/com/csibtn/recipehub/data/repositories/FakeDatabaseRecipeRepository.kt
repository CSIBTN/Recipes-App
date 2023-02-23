package com.csibtn.recipehub.data.repositories

import com.csibtn.recipehub.data.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeDatabaseRecipeRepository : RecipeDatabaseRepositoryInterface {

    private val bookmarkedRecipes: MutableList<Recipe> = mutableListOf()
    private val bookmarkedFlow: MutableStateFlow<List<Recipe>> = MutableStateFlow(bookmarkedRecipes)

    private fun updateRecipes() = bookmarkedFlow.update { bookmarkedRecipes }


    override suspend fun getRecipeById(id: Int): Recipe =
        bookmarkedRecipes.filter { recipes -> recipes.recipeID == id }[0]

    override suspend fun getBookmarkedRecipes(): Flow<List<Recipe>> = bookmarkedFlow

    override suspend fun addRecipe(recipe: Recipe): Unit {
        bookmarkedRecipes.add(recipe)
    }

    override suspend fun removeRecipe(recipe: Recipe) {
        bookmarkedRecipes.remove(recipe)
    }

}