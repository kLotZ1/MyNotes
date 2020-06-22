package com.example.mynotes.viewmodels

import android.R
import android.app.DatePickerDialog
import android.content.Context
import android.widget.ArrayAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynotes.model.api.APIRepository
import com.example.mynotes.model.database.DatabaseRepository
import com.example.mynotes.model.database.Helper
import com.example.mynotes.model.notes.Category
import com.example.mynotes.model.notes.Priority
import com.example.mynotes.model.notes.Task

class RedactActivityViewModel(
    private val helper: Helper,
    databaseRepository: DatabaseRepository,
    private val contextM: Context,
    var view: IRedact
) : ViewModel() {

    var name = ObservableField<String>()

    var description = ObservableField<String>()

    var categories = MutableLiveData<List<Category>>()

    var priorities = MutableLiveData<List<Priority>>()

    var deadline = ObservableField<String>()

    var datePickerListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            deadline.set(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year.toString())
        }

    private val mDatabaseRepository = databaseRepository

    private val apiRepository = APIRepository(helper)

    fun getCategories() {
        apiRepository.getCategories {
            categories.value = it
            setAdapterCategory(contextM)
        }
    }

    fun getPriorities() {
        apiRepository.getPriorities {
            priorities.value = it
            setAdapterPriority(contextM)
        }
    }

    private fun getCategorySpinnerAdapter(context: Context): ArrayAdapter<String> {
        val listCat = categories.value
        val adapter: ArrayAdapter<String>
        listCat?.let {
            val newListString: List<String> = List(listCat.size) { i -> listCat[i].name }
            adapter = ArrayAdapter(
                context,
                R.layout.simple_spinner_item,
                newListString
            )
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            return adapter
        }
        adapter = ArrayAdapter(
            context,
            R.layout.simple_spinner_item,
            List(1) { "" }
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        return adapter
    }

    private fun getPrioritySpinnerAdapter(context: Context): ArrayAdapter<String> {
        val listCat = priorities.value
        val adapter: ArrayAdapter<String>
        listCat?.let {
            val newListString: List<String> = List(listCat.size) { i -> listCat[i].name }
            adapter = ArrayAdapter(
                context,
                R.layout.simple_spinner_item,
                newListString
            )
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            return adapter
        }
        adapter = ArrayAdapter(
            context,
            R.layout.simple_spinner_item,
            List(1) { "" }
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        return adapter
    }


    private fun setAdapterCategory(context: Context){
        view.setAdapterCategory(getCategorySpinnerAdapter(context))

    }
    private fun setAdapterPriority(context: Context){
        view.setAdapterPriority(getPrioritySpinnerAdapter(context))

    }
    fun addTask(task: Task) {
        mDatabaseRepository.createTask(task)
    }


}