package com.mmarengo.android.recipes.data

import com.mmarengo.android.recipes.model.Recipe
import com.mmarengo.android.recipes.model.RecipeDetail
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    suspend fun searchMeals(query: String): Flow<Response<List<Recipe>>>

    suspend fun lookupMeal(mealId: Long): Flow<Response<RecipeDetail>>
}
