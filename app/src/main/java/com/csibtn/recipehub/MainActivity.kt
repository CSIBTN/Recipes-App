package com.csibtn.recipehub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.csibtn.recipehub.databinding.ActivityMainBinding
import com.csibtn.recipehub.ui.RecipeBrowserFragmentDirections

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment?.findNavController()
        mainBinding.bottomNavBar.setOnItemSelectedListener {
            when (it.itemId){
                R.id.search -> {
                    navController?.navigate(RecipeBrowserFragmentDirections.openSearch())
                    true
                }
                R.id.saved -> {
                    navController?.navigate(RecipeBrowserFragmentDirections.showSavedRecipes())
                    true
                }
                else -> false


            }
        }


    }
}