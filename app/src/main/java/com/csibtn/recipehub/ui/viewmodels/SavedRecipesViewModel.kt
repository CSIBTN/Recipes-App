package com.csibtn.recipehub.ui.viewmodels

import com.csibtn.recipehub.data.model.Recipe
import com.csibtn.recipehub.data.repositories.RecipeDatabaseRepository
import com.csibtn.recipehub.data.model.RecipePreview

class SavedRecipesViewModel : RecipeBrowserViewModel() {
    override suspend fun getRecipeById(id: Int): Recipe = RecipeDatabaseRepository.getRecipeById(id)

    suspend fun getSavedRecipes() = RecipeDatabaseRepository.getBookmarkedRecipes()
        .map { recipe -> RecipePreview(recipe.recipeID, recipe.name, recipe.image) }
}