package com.example.mynotes.viewmodels

import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter

interface IRedact {
    fun setAdapterCategory(adapter: ArrayAdapter<String>)
    fun setAdapterPriority(adapter: ArrayAdapter<String>)

}