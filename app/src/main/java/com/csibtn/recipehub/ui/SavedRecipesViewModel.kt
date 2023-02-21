package com.csibtn.recipehub.ui

import com.csibtn.recipehub.data.Recipe
import com.csibtn.recipehub.data.RecipeDatabaseRepository
import com.csibtn.recipehub.data.RecipePreview

class SavedRecipesViewModel : RecipeBrowserViewModel() {
    override suspend fun getRecipeById(id: Int): Recipe = RecipeDatabaseRepository.getRecipeById(id)

    suspend fun getSavedRecipes() = RecipeDatabaseRepository.getBookmarkedRecipes()
        .map { recipe -> RecipePreview(recipe.recipeID, recipe.name, recipe.image) }
}