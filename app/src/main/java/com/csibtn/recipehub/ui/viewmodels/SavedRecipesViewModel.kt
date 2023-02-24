package com.csibtn.recipehub.ui.viewmodels

import com.csibtn.recipehub.data.model.Recipe
import com.csibtn.recipehub.data.repositories.RecipeDatabaseRepository

class SavedRecipesViewModel : RecipeBrowserViewModel() {

    override suspend fun getRecipeById(id: Int): Recipe = RecipeDatabaseRepository.getRecipeById(id)

    suspend fun getSavedRecipes() = RecipeDatabaseRepository.getBookmarkedRecipes()

}