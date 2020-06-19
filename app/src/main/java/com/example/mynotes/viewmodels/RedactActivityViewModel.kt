package com.example.mynotes.viewmodels

import android.app.DatePickerDialog
import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.mynotes.model.api.APIRepository
import com.example.mynotes.model.database.DatabaseRepository
import com.example.mynotes.model.database.Helper
import com.example.mynotes.model.notes.Category
import com.example.mynotes.model.notes.Priority
import com.example.mynotes.model.notes.Task
import java.util.*

class RedactActivityViewModel(
    private val helper: Helper,
    databaseRepository: DatabaseRepository,
    private val contextM: Context
) {

    var name = ObservableField<String>()

    var description = ObservableField<String>()

    var categories = MutableLiveData<List<Category>>()

    var priorities = MutableLiveData<List<Priority>>()

    var deadline = ObservableField<String>()

    private val mDatabaseRepository = databaseRepository

    private val apiRepository = APIRepository(helper)

    fun getCategories() {
        apiRepository.getCategories {
            categories.setValue(it)
        }
    }

    fun getPriorities() {

        apiRepository.getPriorities {
            priorities.setValue(it)
        }
    }

    fun addTask(task: Task) {
        mDatabaseRepository.createTask(task)
    }

    fun selectDate() {

        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)

        val month = calendar.get(Calendar.MONTH)

        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            contextM,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                deadline.set(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year.toString())
            }, year, month, day
        )
        datePickerDialog.show()
    }


}