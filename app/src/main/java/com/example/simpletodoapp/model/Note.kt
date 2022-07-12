package com.example.simpletodoapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simpletodoapp.util.Constants
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constants.DBConst.NOTE_TABLE)
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var description: String
): Parcelable