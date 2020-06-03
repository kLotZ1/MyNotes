package com.example.mynotes.model.notes

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Priority(
    @PrimaryKey @ColumnInfo(name = "id_priority") var id: Int, @ColumnInfo(name = "name_priority") var name: String,
    var color: String
) : Parcelable