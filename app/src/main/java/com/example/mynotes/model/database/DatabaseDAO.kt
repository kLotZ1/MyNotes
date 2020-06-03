package com.example.mynotes.model.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.mynotes.model.authorization.User

@Dao
interface DatabaseDAO{
    @Insert
    fun addUser(user: User)
}