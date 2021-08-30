package com.mmarengo.android.recipes.ui

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.mmarengo.android.recipes.R
import com.mmarengo.android.recipes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Recipes)
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.main_nav_host_fragment)

        binding.mainNavView.setupWithNavController(navController)

    }
}

val mainAppBarConfiguration: AppBarConfiguration by lazy {
    AppBarConfiguration(
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        setOf(
            R.id.homeFragment, R.id.categoriesFragment
        )
    )
}