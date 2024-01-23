package com.example.g55085_android_projet.ShowRecipe

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShowRecipeViewModelFactory(
    private val application: Application,
    private val recipeId: Long,
    private val recipeName: String,
    private val recipeImage: String,
    private val listIngredients: String,
    private val argument: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShowRecipeViewModel::class.java)) {
            return ShowRecipeViewModel(
                application,
                recipeId,
                recipeName,
                recipeImage,
                listIngredients,
                argument
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}