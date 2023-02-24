package com.csibtn.recipehub.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.csibtn.recipehub.data.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM Recipe")
    fun getRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM Recipe Where recipeId= :id ")
    suspend fun getRecipesById(id: Int): Recipe

    @Insert
    suspend fun insertRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("DELETE FROM RECIPE")
    suspend fun deleteAll()


}