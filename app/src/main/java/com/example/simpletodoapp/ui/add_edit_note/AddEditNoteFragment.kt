package com.example.simpletodoapp.ui.add_edit_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.simpletodoapp.R
import com.example.simpletodoapp.databinding.FragmentAddNoteBinding
import com.example.simpletodoapp.model.Note
import com.example.simpletodoapp.ui.note_detail.NoteDetailsFragmentArgs
import com.example.simpletodoapp.util.Validator
import com.google.android.material.textfield.TextInputEditText


class AddEditNoteFragment : Fragment() {

    private val noteViewModel: AddEditNoteViewModel by viewModels()

    private val args by navArgs<NoteDetailsFragmentArgs>()
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!
    private var note: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        getArgsData()
        setViewData()
        initClickListeners()
        return binding.root
    }

    private fun getArgsData() {
        ///args can't be null, though default value on nav graph is null.
        try {
            if(args.note != null){
                note = args.note
            }
        } catch (e: Exception) {
            print(e.message)
        }
    }

    private fun setViewData() {
        if(note != null){
            binding.etTitle.setText(args.note.title)
            binding.etDescription.setText(args.note.description)
            binding.btnAdd.text = "Update Note"
            binding.etTitle.requestFocus()
        }
    }

    private fun initClickListeners() {
        binding.btnAdd.setOnClickListener {
            val validation = verifyDataFromUser(binding.etTitle, binding.etDescription)
            if(validation){
                if(isForEdit()){
                    updateDataToDb()
                }else insertDataToDb()
            }
        }
    }

    private fun insertDataToDb() {
        val mTitle = binding.etTitle.text.toString()
        val mDescription = binding.etDescription.text.toString()
        val newData = Note(
            mTitle,
            mDescription
        )
        noteViewModel.insertData(newData)
        Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    private fun updateDataToDb() {
        val mTitle = binding.etTitle.text.toString()
        val mDescription = binding.etDescription.text.toString()
        this.note?.title = mTitle
        this.note?.description = mDescription
        noteViewModel.updateData(this.note!!)
        Toast.makeText(requireContext(), "Successfully updates!", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    private fun isForEdit(): Boolean {
        return note != null
    }

    private fun verifyDataFromUser(
        mTitle: TextInputEditText,
        mDescription: TextInputEditText
    ): Boolean {

        mTitle.error = if (Validator.NullChecker.isNonNullString(mTitle.text?.toString())) {
            null
        } else {
            "Please write the title"
        }

        mDescription.error =
            if (Validator.NullChecker.isNonNullString(mDescription.text?.toString())) {
                null
            } else {
                "Please write the description"
            }

        return (Validator.NullChecker.isNonNullString(mTitle.text?.toString()) && Validator.NullChecker.isNonNullString(
            mDescription.text?.toString()
        ))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}