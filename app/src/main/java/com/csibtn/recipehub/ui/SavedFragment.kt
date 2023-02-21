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
import com.csibtn.recipehub.data.RecipeDatabaseRepository
import com.csibtn.recipehub.databinding.FragmentSavedRecipesBinding
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
                val savedRecipes = savedViewModel.getSavedRecipes()
                savedFragmentBinding.savedRecipesRecycler.adapter = RecipeBrowserAdapter(
                    savedRecipes,
                    requireContext(),
                    showDetails(),
                    saveRecipe()
                )
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

    private fun saveRecipe(): (id: Int) -> Unit = { recipeId ->
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val recipe = savedViewModel.getRecipeById(recipeId)
                RecipeDatabaseRepository.addRecipe(recipe)
            }
        }
    }

}