package com.example.simpletodoapp.ui.note_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.simpletodoapp.data_source.localdb.NoteDatabase
import com.example.simpletodoapp.model.Note
import com.example.simpletodoapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDao = NoteDatabase.getDatabase(
        application
    ).noteDao()

    private val repository: NoteRepository = NoteRepository(toDoDao)

    fun getData(id: Int): LiveData<Note> = repository.getData(id)

    fun deleteItem(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(note)
        }
    }
}