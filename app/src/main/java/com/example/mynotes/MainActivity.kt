package com.example.mynotes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.model.database.Helper
import com.example.mynotes.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var helper: Helper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        helper = Helper(applicationContext)
        mainActivityViewModel = MainActivityViewModel(helper)

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
        authorizationButton.setOnClickListener {
            mainActivityViewModel.authorization { NotesActivity.start(this@MainActivity) }

        }
        registerButton.setOnClickListener() {
            mainActivityViewModel.registration { NotesActivity.start(this@MainActivity) }
        }
    }

}
