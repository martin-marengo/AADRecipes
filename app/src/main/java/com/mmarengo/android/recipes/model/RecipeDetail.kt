package com.mmarengo.android.recipes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeDetail(
    val id: Long,
    val name: String,
    val category: String,
    val instructions: String,
    val thumbUrl: String?,
    val youtubeUrl: String?,
    val ingredients: List<Ingredient>
) : Parcelable
