package com.example.simpletodoapp.repository

import androidx.lifecycle.LiveData
import com.example.simpletodoapp.data_source.localdb.NoteDao
import com.example.simpletodoapp.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    val getAllData: LiveData<List<Note>> = noteDao.getAllData()

    fun getData(id: Int): LiveData<Note> = noteDao.getData(id)

    suspend fun insertData(toDoData: Note){
        noteDao.insertData(toDoData)
    }

    suspend fun updateData(toDoData: Note){
        noteDao.updateData(toDoData)
    }

    suspend fun deleteItem(toDoData: Note){
        noteDao.deleteItem(toDoData)
    }

    suspend fun deleteAll(){
        noteDao.deleteAll()
    }
}