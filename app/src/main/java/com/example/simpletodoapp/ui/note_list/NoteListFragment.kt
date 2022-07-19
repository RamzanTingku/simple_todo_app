package com.example.simpletodoapp.ui.note_list

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpletodoapp.R
import com.example.simpletodoapp.databinding.FragmentListBinding
import com.example.simpletodoapp.ui.note_list.adapter.NoteListAdapter
import com.example.simpletodoapp.util.isDarkThemeOn


class NoteListFragment : Fragment(), MenuProvider {

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

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

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

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.list_menu, menu)
        if(activity?.isDarkThemeOn() == true){
            menu.findItem(R.id.menu_dark).isChecked = true
        }else{
            menu.findItem(R.id.menu_light).isChecked = true
        }
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.menu_dark -> {
                menuItem.isChecked = !menuItem.isChecked
                if(menuItem.isChecked) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            R.id.menu_light -> {
                menuItem.isChecked = !menuItem.isChecked
                if(menuItem.isChecked) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        return true
    }
}