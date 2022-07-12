package com.example.simpletodoapp.ui

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.simpletodoapp.R
import com.example.simpletodoapp.model.Note
import com.example.simpletodoapp.ui.note_list.NoteListFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapters {

    companion object{

        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean){
            view.setOnClickListener {
                if(navigate){
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
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