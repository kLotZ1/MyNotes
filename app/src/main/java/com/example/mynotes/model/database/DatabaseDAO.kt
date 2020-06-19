package com.example.mynotes.model.database

import androidx.room.*
import com.example.mynotes.model.authorization.User
import com.example.mynotes.model.notes.Category
import com.example.mynotes.model.notes.Priority
import com.example.mynotes.model.notes.Task

@Dao
interface DatabaseDAO{
    @Insert
    fun addUser(user: User)
    @Insert
    fun addTask(task: Task)

    @Insert
    fun addCategory(category: Category)

    @Query("Select * FROM Task")
    fun getAllTasks(): List<Task>

    @Query("Select * FROM Category")
    fun getAllCategories(): List<Category>

    @Query("Select * FROM Priority")
    fun getAllPriorities():List<Priority>

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)


    @Insert
    fun addPriority(priority: Priority)
}