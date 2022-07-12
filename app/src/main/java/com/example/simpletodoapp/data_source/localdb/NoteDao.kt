package com.example.simpletodoapp.data_source.localdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.simpletodoapp.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: Note)

    @Update
    suspend fun updateData(toDoData: Note)

    @Delete
    suspend fun deleteItem(toDoData: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()
}