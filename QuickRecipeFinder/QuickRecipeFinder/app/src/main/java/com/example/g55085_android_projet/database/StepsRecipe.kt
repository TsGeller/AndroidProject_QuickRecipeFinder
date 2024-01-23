package com.example.g55085_android_projet.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stepsFavorites_table")
data class StepsRecipe(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_recipe")
    var id: Long = 0,

    @ColumnInfo(name = "steps_recipes")
    var steps: String
)
