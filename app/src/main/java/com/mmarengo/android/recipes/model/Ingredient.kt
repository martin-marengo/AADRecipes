package com.mmarengo.android.recipes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    val name: String,
    val measure: String? = null
) : Parcelable
