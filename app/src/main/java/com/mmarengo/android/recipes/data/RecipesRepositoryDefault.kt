package com.mmarengo.android.recipes.data

import com.mmarengo.android.recipes.data.network.RecipesApiManager
import com.mmarengo.android.recipes.data.network.model.LookUpRecipeResponse
import com.mmarengo.android.recipes.data.network.model.RecipeSearchDTO
import com.mmarengo.android.recipes.data.network.model.SearchRecipesResponse
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

    override suspend fun searchRecipes(query: String): Flow<Response<List<Recipe>>> =
        withContext(dispatcher) {
            flow {
                emit(Response.InProgress)

                val response: SearchRecipesResponse = recipesApiManager.searchRecipes(query)
                val recipeList: List<Recipe> = response.recipes.map { dto: RecipeSearchDTO ->
                    dto.toModel()
                }
                emit(Response.Success(recipeList))

            }.catch { throwable ->
                emit(Response.Error(throwable))
            }
        }

    override suspend fun lookUpRecipe(recipeId: Long): Flow<Response<RecipeDetail>> =
        withContext(dispatcher) {
            flow {
                emit(Response.InProgress)

                val response: LookUpRecipeResponse = recipesApiManager.lookUpRecipe(recipeId)
                val recipeDetail: RecipeDetail = response.recipes.first().toModel()
                emit(Response.Success(recipeDetail))

            }.catch { throwable ->
                emit(Response.Error(throwable))
            }
        }

    override suspend fun lookUpRandomRecipe(): Flow<Response<RecipeDetail>> =
        withContext(dispatcher) {
            flow {
                emit(Response.InProgress)

                val response: LookUpRecipeResponse = recipesApiManager.lookUpRandomRecipe()
                val recipeDetail: RecipeDetail = response.recipes.first().toModel()
                emit(Response.Success(recipeDetail))
            }
        }.catch { throwable ->
            emit(Response.Error(throwable))
        }
}
