package com.example.mynotes.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mynotes.R
import com.example.mynotes.model.database.Helper
import com.example.mynotes.viewmodels.INotes
import com.example.mynotes.viewmodels.NotesActivityViewModel
import com.example.mynotes.viewmodels.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_notes.*

class NotesActivity : AppCompatActivity(), INotes {
    companion object {
        fun start(context: Context?) =
            context?.startActivity(Intent(context, NotesActivity::class.java))

    }
    lateinit var helper: Helper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        helper = Helper(applicationContext)
        val notesActivityViewModel = NotesActivityViewModel(helper,this)
        notesRecyclerView.layoutManager = notesActivityViewModel.getLayoutManager(this)
        notesActivityViewModel.getList()
        addButton.setOnClickListener{
            RedactActivity.start(this@NotesActivity)
            finish()
        }

    }

    override fun setAdapter(adapter: RecyclerViewAdapter) {
        notesRecyclerView.adapter = adapter
    }
}