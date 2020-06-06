package com.example.mynotes.viewmodels

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mynotes.NotesActivity
import com.example.mynotes.model.api.APIRepository
import com.example.mynotes.model.authorization.User
import com.example.mynotes.model.database.Helper


class MainActivityViewModel(private val applicationContext: Context) : ViewModel() {

    private var helper = Helper(applicationContext)

    var isRegistration = ObservableBoolean(false)

    var isAccessAllowed = ObservableBoolean(false)

    var name = ObservableField<String>()

    var email = ObservableField<String>()

    var password = ObservableField<String>()

    var passwordConfirm = ObservableField<String>()

    private var apiRepository = APIRepository(applicationContext)

    fun registration() {
        if (isRegistration.get()) {
            val _user = User(
                email.get().toString(),
                name.get().toString(),
                password.get().toString()
            ) // Ugly. Rework later
            apiRepository.onRegistration(_user)
            if (checkLogin()) {
                isAccessAllowed.set(true)
            }
        } else {
            registrationSwitch()
        }

    }


    private fun checkLogin(): Boolean {
        return helper.readstring() != "" && helper.readstring() != null
    }

    fun registrationSwitch() {
        if (!isRegistration.get())
            isRegistration.set(true)
        else
            isRegistration.set(false)
    }

}