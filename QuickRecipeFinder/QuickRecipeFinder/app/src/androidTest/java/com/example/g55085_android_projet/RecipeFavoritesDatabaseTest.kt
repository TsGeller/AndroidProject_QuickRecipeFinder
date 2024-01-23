package com.example.g55085_android_projet

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.g55085_android_projet.database.RecipeFavoritesDao
import com.example.g55085_android_projet.database.RecipeFavoritesDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class RecipeFavoritesDatabaseTest {

    private lateinit var recipeDao: RecipeFavoritesDao
    private lateinit var db: RecipeFavoritesDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, RecipeFavoritesDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        recipeDao = db.recipeDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


}