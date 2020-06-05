package com.example.mynotes.model.api

import android.content.Context
import android.widget.Toast
import com.example.mynotes.model.authorization.RegistrationResponse
import com.example.mynotes.model.authorization.User
import com.example.mynotes.model.database.Helper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Future

class APIRepository(private var applicationContext: Context) {

    private val api = Retrofit.Builder().baseUrl("http://practice.mobile.kreosoft.ru/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val apiService = api.create(IAuthorizationAPI::class.java)

    var helper = Helper(applicationContext)

    private var regUser: RegistrationResponse? = null


    private fun onRegistration(user: User) {
        val createUser = apiService.registerUser(user.email, user.name, user.password)

        createUser.enqueue(object : Callback<RegistrationResponse> {
            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Error. onRegistration", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(
                call: Call<RegistrationResponse>,
                response: Response<RegistrationResponse>
            ) {

                if (response.isSuccessful) {
                    val rUser = response.body()
                    rUser?.let {
                        regUser = rUser
                        helper.saveString(rUser.api_token)
                    }
                }
            }
        })

    }

    fun getData(callback: Callback<RegistrationResponse>, user: User) {
        apiService.registerUser(user.email, user.name, user.password).enqueue(callback)
    }

    fun getRegUser(): RegistrationResponse? {
        return regUser
    }
}