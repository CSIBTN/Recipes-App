package com.csibtn.recipehub.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.csibtn.recipehub.data.model.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM Recipe")
    suspend fun getRecipes() : List<Recipe>

    @Query("SELECT * FROM Recipe Where recipeId= :id ")
    suspend fun getRecipesById(id : Int) : Recipe

    @Insert
    suspend fun insertRecipe(recipe: Recipe)

}