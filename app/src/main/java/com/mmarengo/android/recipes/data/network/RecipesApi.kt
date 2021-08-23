package com.mmarengo.android.recipes.data.network

import com.mmarengo.android.recipes.data.network.model.LookUpRecipeResponse
import com.mmarengo.android.recipes.data.network.model.SearchRecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {

    @GET("v1/1/search.php")
    suspend fun searchRecipes(@Query("s") query: String): SearchRecipesResponse

    @GET("v1/1/lookup.php")
    suspend fun lookUpRecipe(@Query("i") id: Long): LookUpRecipeResponse

    @GET("v1/1/random.php")
    suspend fun lookUpRandomRecipe(): LookUpRecipeResponse
}
