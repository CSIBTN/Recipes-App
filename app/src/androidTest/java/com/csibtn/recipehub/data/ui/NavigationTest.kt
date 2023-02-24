package com.csibtn.recipehub.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.csibtn.recipehub.R
import com.csibtn.recipehub.util.Constants
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
@MediumTest
internal class NavigationTest {
    private lateinit var scenario: FragmentScenario<RecipeBrowserFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @After
    fun teardown() {
        scenario.close()
    }

    @Test
    fun moveToTheDetailsFragmentOnClick() {
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }
        onView(withId(R.id.recipeItem)).perform(click())
        verify(navController).navigate(RecipeBrowserFragmentDirections.showRecipeDetails(Constants.stubRecipe))
    }
}