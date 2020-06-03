package com.example.mynotes.model.notes

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Task(
    @PrimaryKey var id: Int,

    var title: String,

    var description: String,

    var done: Int,

    var deadline: Long,

    @Embedded var category: Category,

    @Embedded var priority: Priority,

    var created: Long
): Parcelable