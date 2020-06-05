package com.example.mynotes.viewmodels

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mynotes.model.database.Helper
import java.util.*

class MainActivityViewModel(private val applicationContext: Context) {

    private var helper = Helper(applicationContext)

    private var isRegistration = ObservableField<Boolean>()

    private var name = ObservableField<String>()

    private var email = ObservableField<String>()

    private var password = ObservableField<String>()

    private var passwordConfirm = ObservableField<String>()



}