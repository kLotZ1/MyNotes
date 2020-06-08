package com.example.mynotes.viewmodels

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynotes.NotesActivity
import com.example.mynotes.model.api.APIRepository
import com.example.mynotes.model.authorization.User
import com.example.mynotes.model.database.Helper


class MainActivityViewModel(private val applicationContext: Context) : ViewModel() {

    private var helper = Helper(applicationContext)

    var isRegistration: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    var isAccessAllowed: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    var isPasswordCorrect = MutableLiveData<Boolean>()

    var name = ObservableField<String>()

    var email = ObservableField<String>()

    var password = ObservableField<String>()

    var passwordConfirm = ObservableField<String>()

    private var apiRepository = APIRepository(applicationContext)

    fun registration() {

        if (isRegistration.value!!) {
            isPasswordCorrect.value = checkPassword()
            if (isPasswordCorrect.value!!) {//fix !!

                val _user = User(
                    email.get().toString(),
                    name.get().toString(),
                    password.get().toString()
                ) // Ugly. Rework later

                apiRepository.onRegistration(_user)
                isAccessAllowed.value = checkLogin()
            }
        } else {
            registrationSwitch()
        }

    }

    fun loging() {
        if (isRegistration.value!!) {
            registrationSwitch()
        } else {
            apiRepository.onLogin(email.get().toString(), password.get().toString())
            isAccessAllowed.value = checkLogin()
        }

    }


    private fun checkLogin(): Boolean {
        return helper.readstring() != "" && helper.readstring() != null
    }

    private fun checkPassword(): Boolean {
        return password.get() == passwordConfirm.get()
    }

    private fun registrationSwitch() {
        isRegistration.value = !isRegistration.value!!
    }

}