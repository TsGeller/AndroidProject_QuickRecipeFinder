package com.example.g55085_android_projet.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StepsRecipeDao {

    @Insert
    fun insert(stepsRecipe: StepsRecipe)

    @Delete
    fun delete(stepsRecipe: StepsRecipe)

    @Query("SELECT steps_recipes FROM stepsFavorites_table WHERE id_recipe = :id_recipe")
    fun getStepsById(id_recipe: Long): String

    @Query("DELETE FROM stepsFavorites_table WHERE id_recipe = :idRecipe")
    fun deleteStepsRecipeById(idRecipe: Long)

    @Query("SELECT * FROM stepsFavorites_table")
    fun getallSteps(): List<StepsRecipe>?

}