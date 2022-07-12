package com.example.simpletodoapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.simpletodoapp.util.Constants
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constants.DBConst.NOTE_TABLE)
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String
): Parcelable{
    constructor(title: String, description: String) : this(0, title, description)
}