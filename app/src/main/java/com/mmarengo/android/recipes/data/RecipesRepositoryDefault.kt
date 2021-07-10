package com.mmarengo.android.recipes.data

import com.mmarengo.android.recipes.data.network.RecipesApiManager
import com.mmarengo.android.recipes.data.network.model.LookupMealResponse
import com.mmarengo.android.recipes.data.network.model.MealSearchDTO
import com.mmarengo.android.recipes.data.network.model.SearchMealsResponse
import com.mmarengo.android.recipes.model.Meal
import com.mmarengo.android.recipes.model.MealDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class RecipesRepositoryDefault(
    private val recipesApiManager: RecipesApiManager
): RecipesRepository {

    override suspend fun searchMeals(query: String): Flow<Response<List<Meal>>> = flow {
        emit(Response.Loading)

        val response: SearchMealsResponse = recipesApiManager.searchMeals(query)
        val mealList: List<Meal> = response.meals.map { dto: MealSearchDTO ->
            dto.toModel()
        }
        emit(Response.Success(mealList))

    }.catch { throwable ->
        emit(Response.Error(throwable))
    }

    override suspend fun lookupMeal(mealId: Long): Flow<Response<MealDetail>> = flow {
        emit(Response.Loading)

        val response: LookupMealResponse = recipesApiManager.lookUpMeal(mealId)
        val mealDetail: MealDetail = response.meals.first().toModel()
        emit(Response.Success(mealDetail))

    }.catch { throwable ->
        emit(Response.Error(throwable))
    }
}