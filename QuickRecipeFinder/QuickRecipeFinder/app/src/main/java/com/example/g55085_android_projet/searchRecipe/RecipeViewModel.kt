package com.example.g55085_android_projet.searchRecipe

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.g55085_android_projet.R
import com.example.g55085_android_projet.retrofit.RecipeDto
import com.example.g55085_android_projet.retrofit.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    var listRecipes = MutableLiveData<MutableList<RecipeDto>?>()
    private val applicationContext = application.applicationContext


    public fun searchRecipe(ingredientsList: String) {
        val myHttpCall: Call<List<RecipeDto>?> =
            Repository.client.getRecipesByIngredients(ingredientsList)
        val callback: Callback<List<RecipeDto>?> = object : Callback<List<RecipeDto>?> {
            override fun onFailure(call: Call<List<RecipeDto>?>, t: Throwable) {
                Log.i("failed", "Request")
                Toast.makeText(applicationContext, R.string.request_failed, Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(
                call: Call<List<RecipeDto>?>,
                response: Response<List<RecipeDto>?>
            ) {
                Log.i("Success", "Request")
                val request = call.request()
                val requestMethod = request.method
                val requestUrl = request.url
                Log.i("Request Method", requestMethod)
                Log.i("Request URL", requestUrl.toString())
                if (response.isSuccessful) {
                    listRecipes.value = response.body()?.toMutableList()
                    if (listRecipes.value!!.isEmpty()) {
                        Toast.makeText(applicationContext, R.string.listEmpty, Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(applicationContext, R.string.wrong_request, Toast.LENGTH_SHORT)
                        .show()
                    Log.i("response ", "Not successful")
                }

            }
        }
        myHttpCall.enqueue(callback)
    }


}