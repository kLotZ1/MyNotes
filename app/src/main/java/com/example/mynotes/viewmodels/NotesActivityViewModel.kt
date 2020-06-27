package com.example.mynotes.viewmodels

import androidx.lifecycle.ViewModel
import com.example.mynotes.model.api.APIRepository
import com.example.mynotes.model.database.Helper
import com.example.mynotes.model.notes.Task

class NotesActivityViewModel(val helper: Helper):ViewModel() {
    private var mHelper = helper
    private var apiRepository = APIRepository(mHelper)
    private lateinit var listOfTasks:List<Task>

    fun getList(){

    }
    fun createAdapter():RecyclerViewAdapter{


        return RecyclerViewAdapter()
    }
}