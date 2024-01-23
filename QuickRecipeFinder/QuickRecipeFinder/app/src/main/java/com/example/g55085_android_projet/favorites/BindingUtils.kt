package com.example.g55085_android_projet.favorites

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.g55085_android_projet.database.RecipeFavorites
import com.squareup.picasso.Picasso

@BindingAdapter("recipeId")
fun TextView.getName(item: RecipeFavorites) {
    text = item.name
}

@BindingAdapter("getImageOfRecipe")
fun ImageView.setImageRecipe(item: RecipeFavorites) {
    Picasso.get().load(item.imageUrl).into(this)
}