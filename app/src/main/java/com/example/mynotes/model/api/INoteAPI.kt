package com.example.mynotes.model.api

import com.example.mynotes.model.notes.Category
import com.example.mynotes.model.notes.Priority
import com.example.mynotes.model.notes.Task
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface INoteAPI {
    @GET("categories")
    fun GetCategoriesFromAPI(
        @Query("api_token") api_token: String
    ): Call<List<Category>>

    @POST("tasks")
    fun PostTask(
        @Query("api_token") api_token: String,
        @Query("title") title: String,
        @Query("description") description: String,
        @Query("done") done: Int,
        @Query("deadline") deadline: Long,
        @Query("category_id") category_id: Int,
        @Query("priority_id") priority_id: Int
    ): Call<Task>

    @GET("tasks")
    fun GetTasks(
        @Query("api_token") api_token: String
    ): Call<List<Task>>

    @POST("categories")
    fun PostCategories(
        @Query("name") name: String,
        @Query("api_token") api_token: String
    ): Call<Category>

    @GET("priorities")
    fun GetPriorities(
        @Query("api_token") api_token: String
    ): Call<List<Priority>>

    @PATCH("tasks/{id}")
    fun PatchTask(
        @Path("id") id: Int,
        @Query("api_token") api_token: String,
        @Query("title") title: String,
        @Query("description") description: String,
        @Query("done") done: Int,
        @Query("deadline") deadline: Long,
        @Query("category_id") category_id: Int,
        @Query("priority_id") priority_id: Int
    ): Call<Task>

    @DELETE("tasks/{id}")
    fun DeleteTask(
        @Path("id") id: Int,
        @Query("api_token") api_token: String
    ): Call<ResponseBody>
}