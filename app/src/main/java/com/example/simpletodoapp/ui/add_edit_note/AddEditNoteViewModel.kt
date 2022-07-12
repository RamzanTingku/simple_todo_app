package com.example.simpletodoapp.ui.add_edit_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletodoapp.data_source.localdb.NoteDatabase
import com.example.simpletodoapp.model.Note
import com.example.simpletodoapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEditNoteViewModel (application: Application) : AndroidViewModel(application){
    private val noteDao = NoteDatabase.getDatabase(
        application
    ).noteDao()

    private val repository = NoteRepository(noteDao)

    fun insertData(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(note)
        }
    }

    fun updateData(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(note)
        }
    }

    fun deleteItem(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(note)
        }
    }

}