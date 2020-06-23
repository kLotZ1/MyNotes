package com.example.mynotes.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mynotes.R
import kotlinx.android.synthetic.main.activity_notes.*

class NotesActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context?) =
            context?.startActivity(Intent(context, NotesActivity::class.java))

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        addButton.setOnClickListener(){
            RedactActivity.start(this@NotesActivity)
            finish()
        }
    }
}