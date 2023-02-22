package com.csibtn.recipehub.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.csibtn.recipehub.data.model.Recipe
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class RecipeDaoTest {
    private lateinit var database: RecipeDatabase
    private lateinit var dao: RecipeDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), RecipeDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.recipeDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertRecipe() {
        runTest(UnconfinedTestDispatcher()) {
            val recipe = Recipe(
                1, "Pizza", "someUrl.com", 50.0, 100, 45, "recipes.com", "decent"
            )
            dao.insertRecipe(recipe)
            assertThat(recipe).isEqualTo(dao.getRecipesById(recipe.recipeID))
        }
    }

}