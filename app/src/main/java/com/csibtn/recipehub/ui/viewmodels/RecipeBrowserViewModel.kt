package com.csibtn.recipehub.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csibtn.recipehub.data.model.Recipe
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

    open suspend fun getRecipeById(id: Int): Recipe? =
        recipeBrowserRepository.getRecipeById(id).data?.let { return@let it }

    private suspend fun getRecipesWithTitle(title: String): List<RecipePreview> =
        recipeBrowserRepository.getRecipesWithTitle(title).data

}