package com.example.simpletodoapp.ui.note_list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.simpletodoapp.databinding.FragmentListBinding
import com.example.simpletodoapp.ui.note_list.adapter.NoteListAdapter


class NoteListFragment : Fragment(){

    private val noteListViewModel: NoteListViewModel by viewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val adapter: NoteListAdapter by lazy { NoteListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        noteListViewModel.getAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        setupRecyclerview()

        return binding.root
    }

    private fun setupRecyclerview() {
        val recyclerView = binding.rvNoteList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}