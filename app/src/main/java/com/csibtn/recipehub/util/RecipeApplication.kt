package com.csibtn.recipehub.util

import android.app.Application
import android.content.Context
import com.csibtn.recipehub.data.repositories.RecipeDatabaseRepository

class RecipeApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: RecipeApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}