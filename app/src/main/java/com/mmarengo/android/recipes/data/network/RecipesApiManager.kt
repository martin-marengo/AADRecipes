package com.mmarengo.android.recipes.data.network

import com.mmarengo.android.recipes.data.network.model.LookupMealResponse
import com.mmarengo.android.recipes.data.network.model.SearchMealsResponse

class RecipesApiManager(
    retrofitApiClient: RetrofitApiClient
) {

    private val recipesApi = retrofitApiClient.createService(RecipesApi::class.java)

    suspend fun searchMeals(query: String): SearchMealsResponse =
        recipesApi.searchMeals(query)

    suspend fun lookUpMeal(mealId: Long): LookupMealResponse =
        recipesApi.lookupMeal(mealId)
}
