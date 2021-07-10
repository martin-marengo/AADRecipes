package com.mmarengo.android.recipes.data

import com.mmarengo.android.recipes.model.Meal
import com.mmarengo.android.recipes.model.MealDetail
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    suspend fun searchMeals(query: String): Flow<Response<List<Meal>>>

    suspend fun lookupMeal(mealId: Long): Flow<Response<MealDetail>>
}
