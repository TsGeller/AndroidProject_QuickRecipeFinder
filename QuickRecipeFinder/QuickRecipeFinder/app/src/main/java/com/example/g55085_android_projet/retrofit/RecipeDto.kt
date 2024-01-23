package com.example.g55085_android_projet.retrofit

data class RecipeDto(
    val id: Long,
    val title: String,
    val image: String,
    val usedIngredientCount: Long,
    val missedIngredientCount: Long,
    val missedIngredients: List<IngredientDto>,
    val usedIngredients: List<IngredientDto>,


    ) {

}