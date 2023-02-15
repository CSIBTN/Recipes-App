package com.csibtn.recipehub.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.csibtn.recipehub.databinding.FragmentRecipeDetailsBinding

class RecipeDetailsFragment : Fragment() {
    private val recipeArg : RecipeDetailsFragmentArgs by navArgs()
    private var _recipeDetailsBinding: FragmentRecipeDetailsBinding? = null
    private val recipeDetailsBinding: FragmentRecipeDetailsBinding
        get() = checkNotNull(_recipeDetailsBinding) {
            "Something went wrong with the recipe details binding"
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _recipeDetailsBinding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)
        return recipeDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("Received recipe data : ", "${recipeArg.recipe}")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _recipeDetailsBinding = null
    }
}