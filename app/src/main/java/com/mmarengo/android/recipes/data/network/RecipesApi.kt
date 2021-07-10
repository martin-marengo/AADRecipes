package com.mmarengo.android.recipes.data.network

import com.mmarengo.android.recipes.data.network.model.LookupMealResponse
import com.mmarengo.android.recipes.data.network.model.SearchMealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {

    @GET("v1/1/search.php")
    suspend fun searchMeals(@Query("s") query: String): SearchMealsResponse

    @GET("v1/1/lookup.php")
    suspend fun lookupMeal(@Query("i") id: Long): LookupMealResponse
}
