package com.example.mynotes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel = MainActivityViewModel(applicationContext)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            viewModel = mainActivityViewModel
        }
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        mainActivityViewModel.isPasswordCorrect.observe(this, Observer { isPasswordCorrect ->
            if (!isPasswordCorrect) {
                Toast.makeText(applicationContext, "Password incorrect", Toast.LENGTH_LONG)
                    .show()
            }

        })
    }

}
