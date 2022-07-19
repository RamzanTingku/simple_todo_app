package com.example.simpletodoapp.ui.note_detail

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.example.simpletodoapp.R
import com.example.simpletodoapp.databinding.FragmentNoteDetailsBinding

class NoteDetailsFragment : Fragment() , MenuProvider {

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
            R.id.menu_edit -> {
                return false
            }
            R.id.menu_delete -> {
                deleteNote()
            }
        }
        return true
    }

    private fun deleteNote() {
        
    }
}