package com.example.mynotes.model.api

import com.example.mynotes.model.authorization.LoginResponse
import com.example.mynotes.model.authorization.RegistrationResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface IAuthorizationAPI {
    @POST("register")
    fun registerUser(
        @Query("email") email: String,
        @Query("name") name: String,
        @Query("password") password: String
    ): Call<RegistrationResponse>

    @POST("login")
    fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<LoginResponse>
}
