package com.example.simpletodoapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.simpletodoapp.databinding.FragmentListBinding


class ListFragment : Fragment(){

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}