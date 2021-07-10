package com.mmarengo.android.recipes.model

data class Recipe(
    val id: Long,
    val name: String,
    val category: String,
    val thumbUrl: String?
)
