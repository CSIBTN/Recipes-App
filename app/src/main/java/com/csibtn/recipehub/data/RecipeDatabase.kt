package com.csibtn.recipehub.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.csibtn.recipehub.data.model.Recipe

@Database([Recipe::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}