package com.example.mynotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.apply {
            viewModel = MainActivityViewModel(applicationContext)
        }
        binding.executePendingBindings()
    }
}
