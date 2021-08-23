package com.mmarengo.android.recipes.data.network.model

import com.squareup.moshi.Json

data class LookUpRecipeResponse(
    @Json(name = "meals") val recipes: List<RecipeDetailDTO>
)
