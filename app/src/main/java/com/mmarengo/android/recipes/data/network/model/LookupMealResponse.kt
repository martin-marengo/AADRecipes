package com.mmarengo.android.recipes.data.network.model

import com.squareup.moshi.Json

data class LookupMealResponse(
    @Json(name = "meals") val meals: List<MealDetailDTO>
)
