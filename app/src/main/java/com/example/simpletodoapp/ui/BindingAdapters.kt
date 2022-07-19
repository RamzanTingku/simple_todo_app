package com.example.simpletodoapp.ui

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.simpletodoapp.R
import com.example.simpletodoapp.model.Note
import com.example.simpletodoapp.ui.add_edit_note.AddEditNoteFragmentDirections
import com.example.simpletodoapp.ui.note_detail.NoteDetailsFragmentDirections
import com.example.simpletodoapp.ui.note_list.NoteListFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapters {

    companion object{

        @BindingAdapter("android:navigateToAddNoteFragment")
        @JvmStatic
        fun navigateToAddNoteFragment(view: FloatingActionButton, navigate: Boolean){
            view.setOnClickListener {
                if(navigate){
                    val action = NoteListFragmentDirections.actionListFragmentToAddFragment(null)
                    view.findNavController().navigate(action)
                }
            }
        }

        @BindingAdapter("android:sendDataToNoteDetailFragment")
        @JvmStatic
        fun sendDataToNoteDetailFragment(view: ConstraintLayout, note: Note){
            view.setOnClickListener {
                val action = NoteListFragmentDirections.actionListFragmentToDetailFragment(note)
                view.findNavController().navigate(action)
            }
        }

    }

}