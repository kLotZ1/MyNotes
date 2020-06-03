package com.example.mynotes.model.database

import android.content.Context
import androidx.room.Room
import com.example.mynotes.model.authorization.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DatabaseRepository(applicationContext: Context) {
    private val db =
        Room.databaseBuilder(applicationContext, AppDatabase::class.java,
            DATABASE_FILE_NAME
        )
            .build()


    companion object {
        val DATABASE_FILE_NAME = "database"
    }

    fun createNewUser(user: User) {

        GlobalScope.launch(Dispatchers.IO) {

            db.dao().addUser(user)

        }
    }
}