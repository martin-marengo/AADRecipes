package com.mmarengo.android.recipes

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho

class RecipesApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}
