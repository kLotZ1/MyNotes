package com.example.mynotes.model.api

import android.content.Context
import android.widget.Toast
import com.example.mynotes.model.authorization.LoginResponse
import com.example.mynotes.model.authorization.RegistrationResponse
import com.example.mynotes.model.authorization.User
import com.example.mynotes.model.database.Helper
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class APIRepository(private var applicationContext: Context) {

    private val api = Retrofit.Builder().baseUrl("http://practice.mobile.kreosoft.ru/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val apiService = api.create(IAuthorizationAPI::class.java)

    var helper = Helper(applicationContext)


    fun onRegistration(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            var _user: RegistrationResponse? = null
            try {
                val result = getDataOnRegistration(user)
                if (result.isSuccessful) {
                    val body = result.body()
                    _user = body
                } else {
                    Toast.makeText(applicationContext, "Registration fail", Toast.LENGTH_LONG)
                        .show()
                }
            } catch (e: Exception) {
                _user = null
            }
            withContext(Dispatchers.Main) {
                if (_user != null) {
                    helper.saveString(_user.api_token)
                }
            }
        }
    }

    fun onLogin(email: String, password: String) {
        GlobalScope.launch(Dispatchers.IO) {
            var _apitoken: LoginResponse? = null
            try {
                val result = getDataOnLogin(email, password)
                if (result.isSuccessful) {
                    val body = result.body()
                    _apitoken = body
                } else {
                    Toast.makeText(applicationContext, "Login fail", Toast.LENGTH_LONG)
                        .show()
                }
            } catch (e: Exception) {
                _apitoken = null
            }
            withContext(Dispatchers.Main) {
                if (_apitoken != null) {
                    helper.saveString(_apitoken.api_token)
                }
            }
        }
    }


    private fun getDataOnRegistration(user: User): Response<RegistrationResponse> {
        return apiService.registerUser(user.email, user.name, user.password).execute()
    }

    private fun getDataOnLogin(email: String, password: String): Response<LoginResponse> {
        return apiService.login(email, password).execute()
    }


}