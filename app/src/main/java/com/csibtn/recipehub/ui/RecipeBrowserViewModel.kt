package com.csibtn.recipehub.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csibtn.recipehub.data.Recipe
import com.csibtn.recipehub.data.RecipeDatabaseRepository
import com.csibtn.recipehub.data.RecipePreview
import com.csibtn.recipehub.data.RemoteRecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeBrowserViewModel : ViewModel() {
    private val recipeBrowserRepository = RemoteRecipeRepository
    private val _recipes: MutableStateFlow<List<RecipePreview>> = MutableStateFlow(emptyList())
    val recipes: StateFlow<List<RecipePreview>>
        get() = _recipes.asStateFlow()

    init {
        viewModelScope.launch {
            _recipes.value = getRecipesWithTitle("")
        }
    }

    suspend fun getRecipeById(id: Int): Recipe = recipeBrowserRepository.getRecipeById(id)

    private suspend fun getRecipesWithTitle(title: String): List<RecipePreview> {
        return if (title.isNotEmpty()) {
            recipeBrowserRepository.getRecipesWithTitle(title)
        } else {
            RecipeDatabaseRepository.getBookmarkedRecipes()
                .map { recipe -> RecipePreview(recipe.recipeID, recipe.name, recipe.sourceUrl) }
        }
    }


    private fun transformFullToPreview(listOfRecipes: List<Recipe>): List<RecipePreview> {
        return listOfRecipes.map { recipe ->
            RecipePreview(
                recipe.recipeID,
                recipe.name,
                recipe.sourceUrl
            )
        }
    }
}