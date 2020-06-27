package com.example.mynotes.model.api

import android.widget.Toast
import com.example.mynotes.model.authorization.AuthorizationResponse
import com.example.mynotes.model.authorization.RegistrationResponse
import com.example.mynotes.model.authorization.User
import com.example.mynotes.model.database.Helper
import com.example.mynotes.model.notes.Category
import com.example.mynotes.model.notes.Priority
import com.example.mynotes.model.notes.Task
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class APIRepository(private var helper: Helper) {

    private val api = Retrofit.Builder().baseUrl("http://practice.mobile.kreosoft.ru/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val apiAuthService = api.create(IAuthorizationAPI::class.java)

    private val apiNoteService = api.create(INoteAPI::class.java)

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

    fun postTask(
        title: String,
        description: String,
        deadline: Long,
        category_id: Int,
        priority_id: Int,
        onResult: () -> Unit?
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            var _task: Task? = null
            try {
                val result = getDataOnPostTask(
                    helper.readString(),
                    title,
                    description,
                    deadline,
                    category_id,
                    priority_id
                )
                if(result.isSuccessful){
                    val body = result.body()
                    _task = body
                }
            } catch (e:Exception) {
                _task = null
            }
            withContext(Dispatchers.Main){
                onResult()
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

    fun getCategories(onResult: (cat: List<Category>) -> Unit?) {
        GlobalScope.launch(Dispatchers.IO) {
            val token = mHelper.readString()
            lateinit var categories: List<Category>
            token.let {
                try {
                    val result = getDataCategories(token)
                    if (result.isSuccessful) {
                        val body = result.body()
                        body?.let {
                            categories = body
                        }
                    }
                } catch (e: Exception) {
                    categories = emptyList()
                }
                withContext(Dispatchers.Main) {
                    onResult(categories)
                }
            }
        }
    }

    fun getTasks(onResult: (task: List<Task>) -> Unit?){
        GlobalScope.launch(Dispatchers.IO) {
            val token = mHelper.readString()
            lateinit var tasks:List<Task>
            token.let{
                try {
                    val result = getDataTasks(token)
                    if(result.isSuccessful){
                        val body = result.body()
                        body?.let {
                            tasks = body
                        }
                    }
                }
                catch (e:Exception){
                    tasks = emptyList()
                }
                withContext(Dispatchers.Main){
                    onResult(tasks)
                }

            }
        }
    }

    fun getPriorities(onResult: (cat: List<Priority>) -> Unit?) {
        GlobalScope.launch(Dispatchers.IO) {
            val token = mHelper.readString()
            lateinit var priorities: List<Priority>
            token.let {
                try {
                    val result = getDataPriorities(token)
                    if (result.isSuccessful) {
                        val body = result.body()
                        body?.let {
                            priorities = body
                        }
                    }
                } catch (e: Exception) {
                    priorities = emptyList()
                }
                withContext(Dispatchers.Main) {
                    onResult(priorities)
                }
            }
        }
    }

    private fun getDataCategories(api_token: String): Response<List<Category>> {
        return apiNoteService.GetCategoriesFromAPI(api_token).execute()
    }

    private fun getDataOnPostTask(
        api_token: String,
        title: String,
        description: String,
        deadline: Long,
        category_id: Int,
        priority_id: Int
    ): Response<Task> {
        return apiNoteService.PostTask(
            api_token,
            title,
            description,
            0,
            deadline,
            category_id,
            priority_id
        ).execute()
    }

    private fun getDataPriorities(api_token: String): Response<List<Priority>> {
        return apiNoteService.GetPriorities(api_token).execute()
    }

    private fun getDataTasks(api_token: String): Response<List<Task>> {
        return apiNoteService.GetTasks(api_token).execute()
    }

    private fun getDataOnRegistration(user: User): Response<RegistrationResponse> {
        return apiAuthService.registerUser(user.email, user.name, user.password).execute()
    }

    private fun getDataOnLogin(email: String, password: String): Response<AuthorizationResponse> {
        return apiAuthService.login(email, password).execute()
    }


}