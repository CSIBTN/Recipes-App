package com.csibtn.recipehub.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csibtn.recipehub.data.model.Recipe
import com.csibtn.recipehub.data.repositories.RecipeDatabaseRepository
import com.csibtn.recipehub.data.model.RecipePreview
import com.csibtn.recipehub.data.repositories.RemoteRecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class RecipeBrowserViewModel : ViewModel() {
    private val recipeBrowserRepository = RemoteRecipeRepository
    private val _recipes: MutableStateFlow<List<RecipePreview>> = MutableStateFlow(emptyList())
    val recipes: StateFlow<List<RecipePreview>>
        get() = _recipes.asStateFlow()

    init {
        viewModelScope.launch {
            _recipes.value = getRecipesWithTitle("Strawberry")
        }
    }

   open suspend fun getRecipeById(id: Int): Recipe = recipeBrowserRepository.getRecipeById(id)

    private suspend fun getRecipesWithTitle(title: String): List<RecipePreview> {
        return if (title.isNotEmpty()) {
            recipeBrowserRepository.getRecipesWithTitle(title)
        } else {
            RecipeDatabaseRepository.getBookmarkedRecipes()
                .map { recipe -> RecipePreview(recipe.recipeID, recipe.name, recipe.sourceUrl) }
        }
    }
}