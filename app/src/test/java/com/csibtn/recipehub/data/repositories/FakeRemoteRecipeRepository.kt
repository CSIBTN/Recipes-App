package com.csibtn.recipehub.data.repositories

import com.csibtn.recipehub.data.model.Recipe
import com.csibtn.recipehub.data.model.RecipePreview
import com.csibtn.recipehub.util.Resource
import com.csibtn.recipehub.util.Status

class FakeRemoteRecipeRepository : RemoteRecipeRepositoryInterface {
    private val recipes: MutableList<RecipePreview> = mutableListOf()
    private val fullRecipes: MutableList<Recipe> = mutableListOf()


    override suspend fun getRecipesWithTitle(title: String): Resource<List<RecipePreview>> =
        Resource(Status.SUCCESS, recipes, "Successfully acquired all of the recipes")

    override suspend fun getRecipeById(id: Int): Resource<Recipe?> =
        Resource(
            Status.SUCCESS,
            fullRecipes.filter { recipe -> recipe.recipeID == id }[0],
            "Got recipe with id $id"
        )

}