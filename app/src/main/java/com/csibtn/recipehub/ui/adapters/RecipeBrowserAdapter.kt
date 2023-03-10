package com.csibtn.recipehub.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.csibtn.recipehub.R
import com.csibtn.recipehub.data.model.RecipePreview
import com.csibtn.recipehub.databinding.RecipeItemBinding

class RecipeBrowserAdapter(
    private val recipePreviewList: List<RecipePreview>,
    private val context: Context,
    private val onClickCallback: (id: Int) -> Unit,
    private val onSaveCallback: (id: Int, actionChoice: Boolean) -> Unit,
) :
    RecyclerView.Adapter<RecipeBrowserAdapter.RecipeHolder>() {
    inner class RecipeHolder(
        private val recipeBinding: RecipeItemBinding,
    ) :
        RecyclerView.ViewHolder(recipeBinding.root) {
        fun bind(recipePreview: RecipePreview) {
            recipeBinding.recipeName.text = recipePreview.name
            Glide.with(context).load(recipePreview.imageURL)
                .placeholder(R.drawable.recipe_placeholder)
                .into(recipeBinding.recipePreviewIV)
            recipeBinding.ivBookmark.setOnClickListener {
                Log.d("Icon", "Clicked!")
                recipeBinding.ivBookmark.setImageResource(R.drawable.ic_bookmark_clicked)
            }
            recipeBinding.root.setOnClickListener {
                onClickCallback(recipePreview.recipeId)
            }
            recipeBinding.ivBookmark.setOnClickListener {
                if (recipePreview.isClicked) {
                    onSaveCallback(recipePreview.recipeId, true)
                    recipeBinding.ivBookmark.setImageDrawable(
                        AppCompatResources.getDrawable(context, R.drawable.ic_bookmark)
                    )
                    recipePreview.isClicked = false
                } else {
                    recipeBinding.ivBookmark.setImageDrawable(
                        AppCompatResources.getDrawable(context, R.drawable.ic_bookmark_clicked)
                    )
                    onSaveCallback(recipePreview.recipeId, false)
                    recipePreview.isClicked = true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeItemBinding.inflate(inflater, parent, false)
        return RecipeHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.bind(recipePreviewList[position])
    }

    override fun getItemCount(): Int = recipePreviewList.count()
}