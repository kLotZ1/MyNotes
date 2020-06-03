package com.example.mynotes.model.notes

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Category(
    @PrimaryKey
    @ColumnInfo(name = "id_category") val id: Int, @ColumnInfo(
        name = "name_category"
    ) val name: String
) : Parcelable {}