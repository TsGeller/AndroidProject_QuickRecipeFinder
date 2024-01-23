package com.example.g55085_android_projet.retrofit

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.g55085_android_projet.database.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Repository(application: Application) {
    fun deleteById(recipeId: Long) {
        recipeDao.supprimerRecetteFavoriteParId(recipeId)
    }

    fun deleteStepsbyId(recipeId: Long) {
        stepsRecipesDao.deleteStepsRecipeById(recipeId)
    }

    fun insert(recipeFavorites: RecipeFavorites) {
        recipeDao.insert(recipeFavorites)
    }

    fun insertsteps(stepsRecipe: StepsRecipe) {
        stepsRecipesDao.insert(stepsRecipe)
    }

    fun getIngredientsforRecipe(recipeid: Long): String {
        return recipeDao.getQunatityIngredientsforRecipe(recipeid)
    }

    fun isfound(recipeId: Long): Boolean {
        return recipeDao.isfound(recipeId)
    }

    fun getStepsById(recipeId: Long): String {
        return stepsRecipesDao.getStepsById(recipeId)
    }
    fun getAllfavorites(): LiveData<List<RecipeFavorites>> {
        return recipeDao.getAllRecipeFavorites()
    }

    val recipeDao: RecipeFavoritesDao =
        RecipeFavoritesDatabase.getInstance(application).recipeDao
    val stepsRecipesDao: StepsRecipeDao =
        RecipeFavoritesDatabase.getInstance(application).stepsDao



    companion object {
        val baseUrl = "https://api.spoonacular.com/"
        val jsonConverter: MoshiConverterFactory = MoshiConverterFactory.create()
        val retrofitBuilder: Retrofit.Builder =
            Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(jsonConverter)

        val retrofit: Retrofit = retrofitBuilder.build()
        val client = retrofit.create(RequestHttp::class.java)
    }


}
