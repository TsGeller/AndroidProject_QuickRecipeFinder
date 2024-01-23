package com.example.g55085_android_projet.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestHttp {

    @GET("recipes/findByIngredients?apiKey=cde406aeaa0f4ee8a1d4895b192389cd")
    fun getRecipesByIngredients(@Query("ingredients") listIngredients: String): Call<List<RecipeDto>?>

    @GET("recipes/{recipeId}/analyzedInstructions?apiKey=cde406aeaa0f4ee8a1d4895b192389cd")
    fun getRecipeInstructions(@Path("recipeId") recipeId: String): Call<MutableList<RecipeStepsDto>?>


}