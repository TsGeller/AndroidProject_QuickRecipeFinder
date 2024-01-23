package com.example.g55085_android_projet.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.g55085_android_projet.database.RecipeFavorites
import com.example.g55085_android_projet.database.RecipeFavoritesDao
import com.example.g55085_android_projet.database.RecipeFavoritesDatabase
import androidx.lifecycle.MediatorLiveData
import com.example.g55085_android_projet.retrofit.Repository


class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    val listFavorites = MediatorLiveData<MutableList<RecipeFavorites>?>()
    private val repository: Repository = Repository(application)

    init {
        val originalLiveData = repository.getAllfavorites()
        listFavorites.addSource(originalLiveData) { recipeFavoritesList ->
            val mutableList: MutableList<RecipeFavorites>? = recipeFavoritesList?.toMutableList()
            listFavorites.value = mutableList
        }
    }
}