package com.mmarengo.android.recipes.data.network.model

import com.squareup.moshi.Json

data class SearchMealsResponse(
    @Json(name = "meals") val meals: List<MealSearchDTO>
)
