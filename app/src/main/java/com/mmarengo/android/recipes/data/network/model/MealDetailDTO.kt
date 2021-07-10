package com.mmarengo.android.recipes.data.network.model

import com.mmarengo.android.recipes.model.Ingredient
import com.mmarengo.android.recipes.model.RecipeDetail
import com.squareup.moshi.Json

data class MealDetailDTO(
    @Json(name = "idMeal") val id: Long,
    @Json(name = "strMeal") val name: String,
    @Json(name = "strCategory") val category: String,
    @Json(name = "strInstructions") val instructions: String,
    @Json(name = "strMealThumb") val thumbUrl: String?,
    @Json(name = "strYoutube") val youtubeUrl: String?,
    @Json(name = "strIngredient1") val ingredient1: String?,
    @Json(name = "strIngredient2") val ingredient2: String?,
    @Json(name = "strIngredient3") val ingredient3: String?,
    @Json(name = "strIngredient4") val ingredient4: String?,
    @Json(name = "strIngredient5") val ingredient5: String?,
    @Json(name = "strIngredient6") val ingredient6: String?,
    @Json(name = "strIngredient7") val ingredient7: String?,
    @Json(name = "strIngredient8") val ingredient8: String?,
    @Json(name = "strIngredient9") val ingredient9: String?,
    @Json(name = "strIngredient10") val ingredient10: String?,
    @Json(name = "strIngredient11") val ingredient11: String?,
    @Json(name = "strIngredient12") val ingredient12: String?,
    @Json(name = "strIngredient13") val ingredient13: String?,
    @Json(name = "strIngredient14") val ingredient14: String?,
    @Json(name = "strIngredient15") val ingredient15: String?,
    @Json(name = "strIngredient16") val ingredient16: String?,
    @Json(name = "strIngredient17") val ingredient17: String?,
    @Json(name = "strIngredient18") val ingredient18: String?,
    @Json(name = "strIngredient19") val ingredient19: String?,
    @Json(name = "strIngredient20") val ingredient20: String?,
    @Json(name = "strMeasure1") val measure1: String?,
    @Json(name = "strMeasure2") val measure2: String?,
    @Json(name = "strMeasure3") val measure3: String?,
    @Json(name = "strMeasure4") val measure4: String?,
    @Json(name = "strMeasure5") val measure5: String?,
    @Json(name = "strMeasure6") val measure6: String?,
    @Json(name = "strMeasure7") val measure7: String?,
    @Json(name = "strMeasure8") val measure8: String?,
    @Json(name = "strMeasure9") val measure9: String?,
    @Json(name = "strMeasure10") val measure10: String?,
    @Json(name = "strMeasure11") val measure11: String?,
    @Json(name = "strMeasure12") val measure12: String?,
    @Json(name = "strMeasure13") val measure13: String?,
    @Json(name = "strMeasure14") val measure14: String?,
    @Json(name = "strMeasure15") val measure15: String?,
    @Json(name = "strMeasure16") val measure16: String?,
    @Json(name = "strMeasure17") val measure17: String?,
    @Json(name = "strMeasure18") val measure18: String?,
    @Json(name = "strMeasure19") val measure19: String?,
    @Json(name = "strMeasure20") val measure20: String?,
) {

    fun toModel(): RecipeDetail {
        val ingredients = mutableListOf<Ingredient>()
        if (!ingredient1.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient1, measure1))
        }
        if (!ingredient2.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient2, measure2))
        }
        if (!ingredient3.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient3, measure3))
        }
        if (!ingredient4.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient4, measure4))
        }
        if (!ingredient5.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient5, measure5))
        }
        if (!ingredient6.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient6, measure6))
        }
        if (!ingredient7.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient7, measure7))
        }
        if (!ingredient8.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient8, measure8))
        }
        if (!ingredient9.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient9, measure9))
        }
        if (!ingredient10.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient10, measure10))
        }
        if (!ingredient11.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient11, measure11))
        }
        if (!ingredient12.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient12, measure12))
        }
        if (!ingredient13.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient13, measure13))
        }
        if (!ingredient14.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient14, measure14))
        }
        if (!ingredient15.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient15, measure15))
        }
        if (!ingredient16.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient16, measure16))
        }
        if (!ingredient17.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient17, measure17))
        }
        if (!ingredient18.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient18, measure18))
        }
        if (!ingredient19.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient19, measure19))
        }
        if (!ingredient20.isNullOrBlank()) {
            ingredients.add(Ingredient(ingredient20, measure20))
        }

        return RecipeDetail(
            id = id,
            name = name,
            category = category,
            instructions = instructions,
            thumbUrl = thumbUrl,
            youtubeUrl = youtubeUrl,
            ingredients = ingredients
        )
    }
}
