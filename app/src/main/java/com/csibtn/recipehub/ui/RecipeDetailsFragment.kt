package com.csibtn.recipehub.ui

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.csibtn.recipehub.R
import com.csibtn.recipehub.databinding.FragmentRecipeDetailsBinding
import java.util.*

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
        super.onViewCreated(view, savedInstanceState)
        val recipeLink = "<a href=\"${recipeArg.recipe.sourceUrl}\">Check out this recipe here!</a>"
        Glide.with(requireContext())
            .load(recipeArg.recipe.image)
            .into(recipeDetailsBinding.recipeIconIV)
        recipeDetailsBinding.recipeNameTV.text = recipeArg.recipe.name
        recipeDetailsBinding.likesTV.text = "${recipeArg.recipe.likes}"
        recipeDetailsBinding.healthScoreTV.text = "${recipeArg.recipe.healthScore}"
        recipeDetailsBinding.timeToPrepareTV.text = "${recipeArg.recipe.timeToPrepare}"
        recipeDetailsBinding.summaryTV.text = recipeArg.recipe.summary
        recipeDetailsBinding.recipeLinkTV.text = Html.fromHtml(recipeLink,HtmlCompat.FROM_HTML_MODE_COMPACT)
        recipeDetailsBinding.recipeLinkTV.movementMethod = LinkMovementMethod.getInstance()
        recipeDetailsBinding.summaryTV.text = Html.fromHtml(recipeArg.recipe.summary,HtmlCompat.FROM_HTML_MODE_COMPACT)
        recipeDetailsBinding.summaryTV.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onDestroy() {
        super.onDestroy()
        _recipeDetailsBinding = null
    }
}