package com.csibtn.recipehub.data

import androidx.room.Room
import com.csibtn.recipehub.util.Constants
import com.csibtn.recipehub.util.RecipeApplication
import kotlinx.coroutines.flow.Flow

object RecipeDatabaseRepository {
    private val recipeDatabase: RecipeDatabase =
        Room.databaseBuilder(
            RecipeApplication.applicationContext().applicationContext,
            RecipeDatabase::class.java,
            Constants.databaseName
        ).build()

    fun getBookmarkedRecipes() : Flow<List<Recipe>>  = recipeDatabase.recipeDao().getRecipes()
}