package com.example.simpletodoapp.ui.note_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.simpletodoapp.data_source.localdb.NoteDatabase
import com.example.simpletodoapp.model.Note
import com.example.simpletodoapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteListViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDao = NoteDatabase.getDatabase(
        application
    ).noteDao()
    private val repository: NoteRepository = NoteRepository(toDoDao)

    val getAllData: LiveData<List<Note>> = repository.getAllData

    fun deleteItem(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(note)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}