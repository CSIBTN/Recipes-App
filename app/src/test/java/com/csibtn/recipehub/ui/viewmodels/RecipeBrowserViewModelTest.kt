package com.csibtn.recipehub.ui.viewmodels

import androidx.arch.core.executor.TaskExecutor
import com.csibtn.recipehub.data.repositories.FakeRemoteRecipeRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test


internal class RecipeBrowserViewModelTest {

    private lateinit var recipeBrowserViewModel: RecipeBrowserViewModel
    private val fakeBrowserRepository: FakeRemoteRecipeRepository = FakeRemoteRecipeRepository()

    @Before
    fun setUp() {
        recipeBrowserViewModel = RecipeBrowserViewModel()
    }

}