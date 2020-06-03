package com.example.mynotes.model.database

import android.content.Context

class Helper(val context: Context){
    val sharedPreferences = context.getSharedPreferences("token",
        Context.MODE_PRIVATE
    )
    fun saveString(token: String){

        val editor = sharedPreferences.edit()
        editor.putString("token",token)
        editor.apply()

    }

    fun readstring(): String{
        return sharedPreferences.getString("token","") ?: ""
    }

    fun deleteToken(){
        val editor = sharedPreferences.edit()
        editor.clear()
    }
}