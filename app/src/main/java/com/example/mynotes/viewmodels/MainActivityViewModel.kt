package com.example.mynotes.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynotes.model.api.APIRepository
import com.example.mynotes.model.authorization.User
import com.example.mynotes.model.database.Helper


class MainActivityViewModel(private val helper: Helper) : ViewModel() {

    private var mHelper = helper

    var isRegistration: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    
    var isPasswordCorrect = MutableLiveData<Boolean>()

    var name = ObservableField<String>()

    var email = ObservableField<String>()

    var password = ObservableField<String>()

    var passwordConfirm = ObservableField<String>()

    private var apiRepository = APIRepository(helper)

    fun registration(a: () -> Unit?) {
        isRegistration.value?.let {
            if (it) {
                isPasswordCorrect.value = checkPassword()
                isPasswordCorrect.value?.let { check ->
                    if (check) { //fix !!

                        val _user = User(
                            email.get().toString(),
                            name.get().toString(),
                            password.get().toString()
                        ) // Ugly. Rework later

                        apiRepository.onRegistration(_user,a)
                    }
                }
            } else {
                registrationSwitch()
            }
        }


    }

    fun authorization(a: () -> Unit?) {
        isRegistration.value?.let {
            if (it) { // Ugly. Rework later
                registrationSwitch()
            } else {
                apiRepository.onLogin(email.get().toString(), password.get().toString(), a)

            }
        }


    }


    private fun checkPassword(): Boolean {
        return password.get() == passwordConfirm.get()
    }

    private fun registrationSwitch() {
        isRegistration.value = !isRegistration.value!!
    }

}