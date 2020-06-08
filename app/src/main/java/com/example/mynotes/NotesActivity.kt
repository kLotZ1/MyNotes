package com.example.mynotes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NotesActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context?) =
            context?.startActivity(Intent(context, NotesActivity::class.java))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

    }
}