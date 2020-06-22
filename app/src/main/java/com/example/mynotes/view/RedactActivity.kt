package com.example.mynotes.view

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import com.example.mynotes.model.database.Helper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mynotes.R
import com.example.mynotes.databinding.ActivityRedactionBinding
import com.example.mynotes.model.database.DatabaseRepository
import com.example.mynotes.viewmodels.IRedact
import com.example.mynotes.viewmodels.RedactActivityViewModel
import kotlinx.android.synthetic.main.activity_redaction.*
import java.util.*

class RedactActivity : AppCompatActivity(), IRedact {
    companion object {
        fun start(context: Context?) =
            context?.startActivity(Intent(context, RedactActivity::class.java))

    }

    lateinit var helper: Helper
    lateinit var redactActivityViewModel: RedactActivityViewModel
    lateinit var binding: ActivityRedactionBinding
    lateinit var spinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        helper = Helper(applicationContext)


        redactActivityViewModel = RedactActivityViewModel(
            helper,
            DatabaseRepository(applicationContext),
            applicationContext, this
        )

        binding = DataBindingUtil.setContentView(this, R.layout.activity_redaction)

        binding.viewModel = redactActivityViewModel

        binding.lifecycleOwner = this
        redactActivityViewModel.getCategories()
        redactActivityViewModel.getPriorities()

        binding.executePendingBindings()



        deadlineEditText.setOnClickListener() {

            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)

            val month = calendar.get(Calendar.MONTH)

            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                redactActivityViewModel.datePickerListener, year, month, day
            )
            datePickerDialog.show()

        }

    }

    override fun setAdapterCategory(adapter: ArrayAdapter<String>) {
        categorySpinner.adapter = adapter
    }

    override fun setAdapterPriority(adapter: ArrayAdapter<String>) {
        prioritySpinner.adapter = adapter
    }
}