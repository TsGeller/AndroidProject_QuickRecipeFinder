package com.example.g55085_android_projet.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recipeFavorites_table")
data class RecipeFavorites(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_recipe")
    var id: Long = 0,

    @ColumnInfo(name = "name_recipe")
    var name: String,

    @ColumnInfo(name = "image_url")
    var imageUrl: String,

    @ColumnInfo(name = "List_IngredientsQuantity")
    var ingredients: String

)