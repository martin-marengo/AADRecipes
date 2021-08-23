package com.mmarengo.android.recipes.data.network.model

import com.squareup.moshi.Json

data class SearchRecipesResponse(
    @Json(name = "meals") val recipes: List<RecipeSearchDTO>
)
