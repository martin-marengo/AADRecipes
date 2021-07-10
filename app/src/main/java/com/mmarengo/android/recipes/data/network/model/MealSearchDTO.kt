package com.mmarengo.android.recipes.data.network.model

import com.mmarengo.android.recipes.model.Recipe
import com.squareup.moshi.Json

data class MealSearchDTO(
    @Json(name = "idMeal") val id: Long,
    @Json(name = "strMeal") val name: String,
    @Json(name = "strCategory") val category: String,
    @Json(name = "strMealThumb") val thumbUrl: String?
) {
    fun toModel() : Recipe = Recipe(
        id = id, name = name, category = category, thumbUrl = thumbUrl
    )
}
