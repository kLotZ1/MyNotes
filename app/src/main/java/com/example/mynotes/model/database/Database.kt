package com.example.mynotes.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynotes.model.authorization.User
import com.example.mynotes.model.notes.Category
import com.example.mynotes.model.notes.Priority
import com.example.mynotes.model.notes.Task


@Database(
    entities = [User::class, Category::class, Task::class, Priority::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): DatabaseDAO
}