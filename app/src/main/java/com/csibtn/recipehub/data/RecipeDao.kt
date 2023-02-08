package com.csibtn.recipehub.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM Recipe")
    fun getRecipes() : Flow<List<Recipe>>

    @Insert
    suspend fun insertRecipe(recipe: Recipe)
}