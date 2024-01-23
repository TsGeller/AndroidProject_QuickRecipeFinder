package com.example.g55085_android_projet.ShowRecipe

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.g55085_android_projet.R
import com.example.g55085_android_projet.database.*
import com.example.g55085_android_projet.retrofit.RecipeStepsDto
import com.example.g55085_android_projet.retrofit.Repository
import com.example.g55085_android_projet.retrofit.Step
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowRecipeViewModel(
    application: Application,
    recipeId: Long,
    recipeName: String,
    recipeImage: String,
    ingredients: String,
    argument: String
) : AndroidViewModel(application) {

    private lateinit var value: RecipeFavorites
    var recipeId: Long = recipeId
    var recipeName: String = recipeName
    var isFavorites: Boolean = false
    var listStep: MutableLiveData<MutableList<Step>?> = MutableLiveData()
    var recipeImage: String = recipeImage
    var ingredients: String = ingredients
    private val originFragment: String = argument
    private val repository: Repository = Repository(application)

    init {

        isFavorites = repository.isfound(recipeId)
        if (originFragment == "favorites") {
            this.ingredients =repository.getIngredientsforRecipe(recipeId)
            repository.getStepsById(recipeId)
            val stringStep: String = repository.getStepsById(recipeId)
            convert(stringStep)
        } else {
            makeCall(application)
        }


    }

    private fun convert(stringStep: String) {
        val steps: MutableList<Step> = mutableListOf()
        val stepStringList = stringStep.split("|")
        if (stepStringList != null) {
            for (index in stepStringList.indices) {
                val stepString = stepStringList[index]
                val step = Step(number = index, step = stepString.trim())
                steps.add(step)
            }
        }

        listStep.value = steps

    }

    fun addFavorites() {
        if (!isFavorites) {
            val recipeFavorites = RecipeFavorites(
                id = recipeId,
                name = recipeName,
                imageUrl = recipeImage,
                ingredients = ingredients

            )
            val stepsRecipe = StepsRecipe(
                id = recipeId,
                steps = listStep.value!!.joinToString("|") { it.step }
            )
            repository.insertsteps(stepsRecipe)
            repository.insert(recipeFavorites)
        }
        isFavorites = true
    }

    fun deleteFavorites() {
        if (isFavorites) {
            repository.deleteById(recipeId)
            repository.deleteStepsbyId(recipeId)
        }
        isFavorites = false
    }

    private fun makeCall(application: Application) {
        val myHttpCall: Call<MutableList<RecipeStepsDto>?> =
            Repository.client.getRecipeInstructions(recipeId.toString())
        val callback: Callback<MutableList<RecipeStepsDto>?> =
            object : Callback<MutableList<RecipeStepsDto>?> {
                override fun onFailure(call: Call<MutableList<RecipeStepsDto>?>, t: Throwable) {
                    Log.i("ShowRecipeViewModel", "Requete failed")
                    t.toString()
                    Toast.makeText(application, R.string.request_failed, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<MutableList<RecipeStepsDto>?>,
                    response: Response<MutableList<RecipeStepsDto>?>
                ) {
                    if (response.isSuccessful) {
                        val request = call.request()
                        val requestMethod = request.method
                        val requestUrl = request.url
                        Log.i("Request Method", requestMethod)
                        Log.i("Request URL", requestUrl.toString())
                        val stepsList: MutableList<RecipeStepsDto>? = response.body()
                        if (stepsList != null) {
                            listStep.value =
                                stepsList.get(0).steps.toMutableList() // 0 car toujours premier element
                        }
                    } else {
                        Log.i("ShowRecipeViewModel", "erreur")
                    }
                }
            }
        myHttpCall.enqueue(callback)
    }
}
