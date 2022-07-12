package com.example.simpletodoapp.data_source.localdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.simpletodoapp.model.Note
import com.example.simpletodoapp.util.Constants

@Dao
interface NoteDao {

    @Query("SELECT * FROM ${Constants.DBConst.NOTE_TABLE} ORDER BY id ASC")
    fun getAllData(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: Note)

    @Update
    suspend fun updateData(toDoData: Note)

    @Delete
    suspend fun deleteItem(toDoData: Note)

    @Query("DELETE FROM ${Constants.DBConst.NOTE_TABLE}")
    suspend fun deleteAll()
}