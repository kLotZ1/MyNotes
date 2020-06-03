package com.example.mynotes.model.authorization

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey var email: String,

    var name: String,

    var password: String
)