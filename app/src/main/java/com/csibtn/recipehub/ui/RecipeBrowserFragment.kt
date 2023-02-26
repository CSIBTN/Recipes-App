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
import com.csibtn.recipehub.databinding.FragmentRecipeBrowserBinding
import com.csibtn.recipehub.ui.adapters.RecipeBrowserAdapter
import com.csibtn.recipehub.ui.viewmodels.RecipeBrowserViewModel
import kotlinx.coroutines.launch

class RecipeBrowserFragment : Fragment() {
    private val recipeViewModel: RecipeBrowserViewModel by viewModels()
    private var _recipeBrowserBinding: FragmentRecipeBrowserBinding? = null
    private val recipeBrowserBinding: FragmentRecipeBrowserBinding
        get() = checkNotNull(_recipeBrowserBinding) {
            "Something went wrong with the recipe browser binding"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _recipeBrowserBinding = FragmentRecipeBrowserBinding.inflate(inflater, container, false)
        return recipeBrowserBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeBrowserBinding.recipeRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                recipeViewModel.recipes.collect { recipes ->
                    recipeBrowserBinding.recipeRecycler.adapter =
                        RecipeBrowserAdapter(recipes, requireContext(), showDetails(), saveRecipe())
                }
            }
        }
    }


    private fun showDetails(): (id: Int) -> Unit = { recipeId ->
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val recipe = recipeViewModel.getRecipeById(recipeId)
                if (recipe != null) {
                    findNavController().navigate(
                        RecipeBrowserFragmentDirections.showRecipeDetails(
                            recipe
                        )
                    )
                }
            }
        }
    }

    private fun saveRecipe(): (id: Int, actionChoice: Boolean) -> Unit = { recipeId, actionChoice ->
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val recipe = recipeViewModel.getRecipeById(recipeId)
                if (actionChoice) {
                    recipe?.let {
                        recipeViewModel.deleteRecipe(it)
                    }
                } else {
                    recipe?.let {
                        recipeViewModel.addRecipe(it)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _recipeBrowserBinding = null
    }
}
