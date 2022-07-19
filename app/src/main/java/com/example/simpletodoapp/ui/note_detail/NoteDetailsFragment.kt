package com.example.simpletodoapp.ui.note_detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.simpletodoapp.R
import com.example.simpletodoapp.databinding.FragmentNoteDetailsBinding

class NoteDetailsFragment : Fragment() , MenuProvider {

    private val noteViewModel: NoteDetailsViewModel by viewModels()
    private val args by navArgs<NoteDetailsFragmentArgs>()
    private var _binding: FragmentNoteDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNoteDetailsBinding.inflate(inflater, container, false)
        binding.note = args.note

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        noteViewModel.getData(args.note.id).observe(viewLifecycleOwner, Observer {
            binding.note = it
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.edit_menue, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
            }
            R.id.menu_edit -> {
                editNote()
            }
            R.id.menu_delete -> {
                deleteNote()
            }
        }
        return true
    }

    private fun editNote() {
        findNavController().graph.findNode(R.id.addNoteFragment)?.label = "Edit Note"
        val action = NoteDetailsFragmentDirections.actionNoteDetailFragmentToAddNoteFragment(args.note)
        findNavController().navigate(action)
    }

    private fun deleteNote() {
        noteViewModel.deleteItem(args.note)
        Toast.makeText(requireContext(), "Successfully deleted!", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }
}