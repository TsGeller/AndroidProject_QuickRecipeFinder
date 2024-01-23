package com.example.g55085_android_projet.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeFavoritesDao {
    @Insert
    fun insert(recipeFavorite: RecipeFavorites)

    @Delete
    fun delete(recipeFavorite: RecipeFavorites)

    @Query("SELECT * FROM recipeFavorites_table")
    fun getAllRecipeFavorites(): LiveData<List<RecipeFavorites>>

    @Query("SELECT * FROM recipeFavorites_table WHERE id_recipe = :id_recipe")
    fun getRecipeFavoriteById(id_recipe: Long): LiveData<RecipeFavorites>?

    @Query("SELECT EXISTS(SELECT 1 FROM recipeFavorites_table WHERE id_recipe = :id_recipe LIMIT 1)")
    fun isfound(id_recipe: Long): Boolean

    @Query("DELETE FROM recipeFavorites_table WHERE id_recipe = :id_recipe")
    fun supprimerRecetteFavoriteParId(id_recipe: Long)

    @Query("SELECT List_IngredientsQuantity FROM recipeFavorites_table WHERE id_recipe = :id_recipe")
    fun getQunatityIngredientsforRecipe(id_recipe: Long): String

}