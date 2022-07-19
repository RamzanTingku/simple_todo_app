package com.example.simpletodoapp.ui.note_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodoapp.databinding.NoteListRowLayoutBinding
import com.example.simpletodoapp.model.Note


class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.MyViewHolder>() {

    var dataList = emptyList<Note>()

    class MyViewHolder(private val binding: NoteListRowLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(Note: Note){
            binding.note = Note
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NoteListRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(
                    binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(
            parent
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    fun setData(Note: List<Note>){
        this.dataList = Note
        notifyDataSetChanged()
    }

}