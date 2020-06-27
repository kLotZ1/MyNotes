package com.example.mynotes.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.model.api.APIRepository
import com.example.mynotes.model.database.Helper
import com.example.mynotes.model.notes.Task

class NotesActivityViewModel(private val helper: Helper, var view:INotes):ViewModel() {
    private var mHelper = helper
    private var apiRepository = APIRepository(mHelper)
    private lateinit var listOfTasks:List<Task>
    lateinit var  adapter: RecyclerViewAdapter

    fun getList(){
        apiRepository.getTasks {
            listOfTasks = it
            createAdapter()
            setRecyclerAdapter()
        }
    }
    private fun createAdapter():RecyclerViewAdapter{
        adapter = RecyclerViewAdapter(listOfTasks)
        return adapter
    }
    fun getLayoutManager(context: Context): RecyclerView.LayoutManager{
        return LinearLayoutManager(context)
    }
    private fun setRecyclerAdapter(){
        view.setAdapter(adapter)
    }
}