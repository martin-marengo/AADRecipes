package com.mmarengo.android.recipes.data.network

import com.mmarengo.android.recipes.data.network.model.LookUpRecipeResponse
import com.mmarengo.android.recipes.data.network.model.SearchRecipesResponse

class RecipesApiManager(
    retrofitApiClient: RetrofitApiClient
) {

    private val recipesApi = retrofitApiClient.createService(RecipesApi::class.java)

    suspend fun searchRecipes(query: String): SearchRecipesResponse =
        recipesApi.searchRecipes(query)

    suspend fun lookUpRecipe(mealId: Long): LookUpRecipeResponse =
        recipesApi.lookUpRecipe(mealId)

    suspend fun lookUpRandomRecipe(): LookUpRecipeResponse = recipesApi.lookUpRandomRecipe()
}
