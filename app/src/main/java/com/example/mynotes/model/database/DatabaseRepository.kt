package com.example.mynotes.model.database

import android.content.Context
import androidx.room.Room
import com.example.mynotes.model.authorization.User
import com.example.mynotes.model.notes.Category
import com.example.mynotes.model.notes.Priority
import com.example.mynotes.model.notes.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
    fun createTask(task: Task) {
        GlobalScope.launch(Dispatchers.IO) {
            db.dao().addTask(task)
        }
    }

    fun getTasks(onResult: (tasks: List<Task>) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val tasks = db.dao().getAllTasks()
            withContext(Dispatchers.Main){
                onResult(tasks)
            }
        }
    }
    fun createCategory(category: Category) {
        GlobalScope.launch(Dispatchers.IO) {
            db.dao().addCategory(category)
        }
    }

    fun getCategories(onResult: (tasks: List<Category>) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val tasks = db.dao().getAllCategories()
            withContext(Dispatchers.Main){
                onResult(tasks)
            }
        }
    }

    fun createPriority(priority: Priority) {
        GlobalScope.launch(Dispatchers.IO) {
            db.dao().addPriority(priority)
        }
    }

    fun getPriorities(onResult: (tasks: List<Priority>) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val tasks = db.dao().getAllPriorities()
            withContext(Dispatchers.Main){
                onResult(tasks)
            }
        }
    }
}