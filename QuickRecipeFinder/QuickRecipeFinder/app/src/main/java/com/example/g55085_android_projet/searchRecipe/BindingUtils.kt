package com.example.g55085_android_projet.searchRecipe

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.g55085_android_projet.retrofit.RecipeDto
import com.squareup.picasso.Picasso

@BindingAdapter("recipeId")
fun TextView.getName(item: RecipeDto) {
    text = item.title
}

@BindingAdapter("recipeUsedIngredients")
fun TextView.getUsedIngredients(item: RecipeDto) {
    val ingredientsNames = item.usedIngredients.map { it.original }
    text = "Ingrédients utilisés : ${ingredientsNames.joinToString(", ")}"
}

@BindingAdapter("getImageOfRecipe")
fun ImageView.setImageRecipe(item: RecipeDto) {
    Picasso.get().load(item.image).into(this)
}

@BindingAdapter("recipeMissingIngredients")
fun TextView.getMissingIngredients(item: RecipeDto) {
    val ingredientsNames = item.missedIngredients.map { it.original }
    text = "Ingrédients manquants : ${ingredientsNames.joinToString(", ")}"
}


