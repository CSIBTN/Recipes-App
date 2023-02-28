package com.csibtn.recipehub.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.csibtn.recipehub.R
import com.csibtn.recipehub.databinding.FragmentSearchBinding
import com.csibtn.recipehub.ui.adapters.RecipeBrowserAdapter
import com.csibtn.recipehub.ui.viewmodels.RecipeBrowserViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
    private val recipeViewModel: RecipeBrowserViewModel by viewModels()
    private var _searchFragmentBinding: FragmentSearchBinding? = null
    private val searchFragmentBinding: FragmentSearchBinding
        get() = checkNotNull(_searchFragmentBinding) {
            "Something went wrong with the search binding"
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _searchFragmentBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return searchFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<TextInputEditText>(R.id.search_bar)
            .setOnEditorActionListener { textView, _, _ ->
                val inputManager =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(textView.windowToken, 0)

                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        recipeViewModel.setQuery(textView.text.toString())
                    }
                }
                true
            }
        searchFragmentBinding.searchRV.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                recipeViewModel.recipes.collect { recipes ->
                    searchFragmentBinding.searchRV.adapter = RecipeBrowserAdapter(
                        recipes, requireContext(), showDetails(), saveRecipe()
                    )
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
                        SearchFragmentDirections.showRecipeDetails(
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
        _searchFragmentBinding = null
    }
}