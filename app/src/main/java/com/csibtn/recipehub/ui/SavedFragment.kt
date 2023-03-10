package com.csibtn.recipehub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.csibtn.recipehub.data.model.RecipePreview
import com.csibtn.recipehub.databinding.FragmentSavedRecipesBinding
import com.csibtn.recipehub.ui.adapters.RecipeBrowserAdapter
import com.csibtn.recipehub.ui.viewmodels.SavedRecipesViewModel
import kotlinx.coroutines.launch

class SavedFragment : Fragment() {
    private val savedViewModel: SavedRecipesViewModel by viewModels()
    private var _savedFragmentBinding: FragmentSavedRecipesBinding? = null
    private val savedFragmentBinding: FragmentSavedRecipesBinding
        get() = checkNotNull(_savedFragmentBinding) {
            "Something went wrong with the saved recipes binding"
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _savedFragmentBinding = FragmentSavedRecipesBinding.inflate(inflater, container, false)
        savedFragmentBinding.savedRecipesRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return savedFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val savedRecipesFlow = savedViewModel.getSavedRecipes()
                savedRecipesFlow.collect { savedRecipes ->
                    savedFragmentBinding.savedRecipesRecycler.adapter = RecipeBrowserAdapter(
                        savedRecipes.map { recipe ->
                            RecipePreview(
                                recipe.recipeID,
                                recipe.name,
                                recipe.image
                            )
                        },
                        requireContext(),
                        showDetails(),
                        saveRecipe()
                    )
                }
            }
        }
    }


    private fun showDetails(): (id: Int) -> Unit = { recipeId ->
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val recipe = savedViewModel.getRecipeById(recipeId)
                findNavController().navigate(
                    SavedFragmentDirections.showRecipeDetails(
                        recipe
                    )
                )
            }
        }
    }

    private fun saveRecipe(): (id: Int, actionChoice: Boolean) -> Unit = { recipeId, actionChoice ->
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val recipe = savedViewModel.getRecipeById(recipeId)
                if (actionChoice) {
                    savedViewModel.deleteRecipe(recipe)

                } else {
                    savedViewModel.addRecipe(recipe)
                }
            }
        }
    }

}