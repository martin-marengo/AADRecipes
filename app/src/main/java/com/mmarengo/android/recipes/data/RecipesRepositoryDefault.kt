package com.mmarengo.android.recipes.data

import com.mmarengo.android.recipes.data.network.RecipesApiManager
import com.mmarengo.android.recipes.data.network.model.LookupMealResponse
import com.mmarengo.android.recipes.data.network.model.MealSearchDTO
import com.mmarengo.android.recipes.data.network.model.SearchMealsResponse
import com.mmarengo.android.recipes.model.Recipe
import com.mmarengo.android.recipes.model.RecipeDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RecipesRepositoryDefault(
    private val recipesApiManager: RecipesApiManager,
    private val dispatcher: CoroutineDispatcher
): RecipesRepository {

    override suspend fun searchMeals(query: String): Flow<Response<List<Recipe>>> =
        withContext(dispatcher) {
            flow {
                emit(Response.InProgress)

                val response: SearchMealsResponse = recipesApiManager.searchMeals(query)
                val recipeList: List<Recipe> = response.meals.map { dto: MealSearchDTO ->
                    dto.toModel()
                }
                emit(Response.Success(recipeList))

            }.catch { throwable ->
                emit(Response.Error(throwable))
            }
        }

    override suspend fun lookupMeal(mealId: Long): Flow<Response<RecipeDetail>> =
        withContext(dispatcher) {
            flow {
                emit(Response.InProgress)

                val response: LookupMealResponse = recipesApiManager.lookUpMeal(mealId)
                val recipeDetail: RecipeDetail = response.meals.first().toModel()
                emit(Response.Success(recipeDetail))

            }.catch { throwable ->
                emit(Response.Error(throwable))
            }
        }
}
