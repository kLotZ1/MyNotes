package com.example.mynotes.model.api

import android.widget.Toast
import com.example.mynotes.model.authorization.AuthorizationResponse
import com.example.mynotes.model.authorization.RegistrationResponse
import com.example.mynotes.model.authorization.User
import com.example.mynotes.model.database.Helper
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class APIRepository(private var helper: Helper) {

    private val api = Retrofit.Builder().baseUrl("http://practice.mobile.kreosoft.ru/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val apiService = api.create(IAuthorizationAPI::class.java)

    private var mHelper = helper


    fun onRegistration(user: User, onResult: () -> Unit?) {
        GlobalScope.launch(Dispatchers.IO) {
            var _user: RegistrationResponse? = null
            try {
                val result = getDataOnRegistration(user)
                if (result.isSuccessful) {
                    val body = result.body()
                    _user = body
//                } else {
//                    Toast.makeText(applicationContext, "Registration fail", Toast.LENGTH_LONG)
//                        .show()
                }
            } catch (e: Exception) {
                _user = null
            }
            withContext(Dispatchers.Main) {
                if (_user != null) {
                    mHelper.saveString(_user.api_token)
                    onResult()
                }
            }
        }
    }

    fun onLogin(email: String, password: String, onResult: () -> Unit?) {
        GlobalScope.launch(Dispatchers.IO) {
            var _apitoken: AuthorizationResponse? = null
            try {
                val result = getDataOnLogin(email, password)
                if (result.isSuccessful) {
                    val body = result.body()
                    _apitoken = body
//                } else {
//                    Toast.makeText(applicationContext, "Login fail", Toast.LENGTH_LONG)
//                        .show()
                }
            } catch (e: Exception) {
                _apitoken = null
            }
            withContext(Dispatchers.Main) {
                if (_apitoken != null) {
                    mHelper.saveString(_apitoken.api_token)
                    onResult()
                }
            }
        }
    }


    private fun getDataOnRegistration(user: User): Response<RegistrationResponse> {
        return apiService.registerUser(user.email, user.name, user.password).execute()
    }

    private fun getDataOnLogin(email: String, password: String): Response<AuthorizationResponse> {
        return apiService.login(email, password).execute()
    }


}