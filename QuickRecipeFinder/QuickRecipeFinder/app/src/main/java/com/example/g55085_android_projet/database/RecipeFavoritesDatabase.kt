package com.example.g55085_android_projet.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [RecipeFavorites::class, StepsRecipe::class],
    version = 1,
    exportSchema = false
)
abstract class RecipeFavoritesDatabase : RoomDatabase() {
    abstract val recipeDao: RecipeFavoritesDao
    abstract val stepsDao: StepsRecipeDao

    companion object {

        @Volatile
        private var INSTANCE: RecipeFavoritesDatabase? = null

        fun getInstance(context: Context): RecipeFavoritesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecipeFavoritesDatabase::class.java,
                        "Recipe_history_database"
                    )
                        .fallbackToDestructiveMigration().allowMainThreadQueries().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}