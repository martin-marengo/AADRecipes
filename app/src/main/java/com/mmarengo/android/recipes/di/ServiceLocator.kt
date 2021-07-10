package com.mmarengo.android.recipes.di

import com.mmarengo.android.recipes.BuildConfig
import com.mmarengo.android.recipes.data.RecipesRepository
import com.mmarengo.android.recipes.data.RecipesRepositoryDefault
import com.mmarengo.android.recipes.data.network.RecipesApiManager
import com.mmarengo.android.recipes.data.network.RetrofitApiClient

object ServiceLocator {

    @Volatile
    private var recipesRepository: RecipesRepository? = null

    fun provideRecipesRepository(): RecipesRepository {
        synchronized(this) {
            return recipesRepository ?: createRecipesRepository()
        }
    }

    private fun createRecipesRepository(): RecipesRepository {
        val retrofitApiClient = RetrofitApiClient(
            baseUrl = BuildConfig.RECIPES_BASE_URL,
            loggingEnabled = BuildConfig.DEBUG
        )
        val recipesApiManager = RecipesApiManager(retrofitApiClient)
        val newRecipesRepository = RecipesRepositoryDefault(recipesApiManager)
        recipesRepository = newRecipesRepository
        return newRecipesRepository
    }
}
