package com.csibtn.recipehub.data.repositories

import com.csibtn.recipehub.data.model.Recipe
import com.csibtn.recipehub.data.model.RecipePreview
import com.csibtn.recipehub.util.Resource

interface RemoteRecipeRepositoryInterface {
    suspend fun getRecipesWithTitle(title: String): Resource<List<RecipePreview>>
    suspend fun getRecipeById(id: Int): Resource<Recipe?>
}