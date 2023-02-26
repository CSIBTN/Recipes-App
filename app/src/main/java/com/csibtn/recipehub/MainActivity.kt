package com.csibtn.recipehub

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.csibtn.recipehub.databinding.ActivityMainBinding
import com.csibtn.recipehub.ui.viewmodels.RecipeBrowserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var _mainBinding: ActivityMainBinding
    private val recipeViewModel: RecipeBrowserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_mainBinding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment?.findNavController()

        if (navController != null) {
            NavigationUI.setupWithNavController(_mainBinding.bottomNavBar, navController)
        }

        _mainBinding.searchBar.also {
            it.onFocusChangeListener = View.OnFocusChangeListener { p0, p1 ->
                if (p1)
                    navController?.navigate(R.id.searchFragment)
            }
        }

    }
}