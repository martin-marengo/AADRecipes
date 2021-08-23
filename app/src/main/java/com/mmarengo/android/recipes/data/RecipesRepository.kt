package com.mmarengo.android.recipes.data

import com.mmarengo.android.recipes.model.Recipe
import com.mmarengo.android.recipes.model.RecipeDetail
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    suspend fun searchRecipes(query: String): Flow<Response<List<Recipe>>>

    suspend fun lookUpRecipe(mealId: Long): Flow<Response<RecipeDetail>>

    suspend fun lookUpRandomRecipe(): Flow<Response<RecipeDetail>>
}
