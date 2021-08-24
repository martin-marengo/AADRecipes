package com.mmarengo.android.recipes.ui

import androidx.navigation.ui.AppBarConfiguration
import com.mmarengo.android.recipes.R

val mainAppBarConfiguration: AppBarConfiguration by lazy {
    AppBarConfiguration(
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        setOf(
            R.id.homeFragment, R.id.categoriesFragment
        )
    )
}
