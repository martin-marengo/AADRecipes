package com.mmarengo.android.recipes.model

data class MealDetail(
    val id: Long,
    val name: String,
    val category: String,
    val instructions: String,
    val thumbUrl: String?,
    val youtubeUrl: String?,
    val ingredients: List<Ingredient>
)
