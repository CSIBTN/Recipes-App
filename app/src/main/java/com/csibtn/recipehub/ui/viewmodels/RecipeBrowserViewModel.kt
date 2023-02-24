package com.csibtn.recipehub.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csibtn.recipehub.data.model.Recipe
import com.csibtn.recipehub.data.model.RecipePreview
import com.csibtn.recipehub.data.repositories.RecipeDatabaseRepository
import com.csibtn.recipehub.data.repositories.RemoteRecipeRepository
import com.csibtn.recipehub.data.repositories.SharedPreferencesRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

open class RecipeBrowserViewModel : ViewModel() {
    private val _recipes: MutableStateFlow<List<RecipePreview>> = MutableStateFlow(emptyList())
    val recipes: StateFlow<List<RecipePreview>>
        get() = _recipes.asStateFlow()


    private val _lastQuery: MutableStateFlow<String> = MutableStateFlow("")
    val lastQuery: StateFlow<String>
        get() = _lastQuery.asStateFlow()

    init {
        viewModelScope.launch {
            SharedPreferencesRepository.storedQuery.collectLatest {
                val newRecipes = getRecipesWithTitle(it)
                _recipes.update { newRecipes }
            }
        }
    }

    suspend fun setQuery(query: String) = SharedPreferencesRepository.setStoredQuery(query)

    open suspend fun getRecipeById(id: Int): Recipe? =
        RemoteRecipeRepository.getRecipeById(id).data?.let { return@let it }

    private suspend fun getRecipesWithTitle(title: String): List<RecipePreview> =
        RemoteRecipeRepository.getRecipesWithTitle(title).data

    suspend fun addRecipe(recipe: Recipe) = RecipeDatabaseRepository.addRecipe(recipe)


}