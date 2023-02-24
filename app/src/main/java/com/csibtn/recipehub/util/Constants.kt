package com.csibtn.recipehub.util

import com.csibtn.recipehub.data.model.Recipe

object Constants {
    const val databaseName = "Recipes"
    val stubRecipe = Recipe(1, "", "", timeToPrepare = 0, sourceUrl = "", summary = "")
}