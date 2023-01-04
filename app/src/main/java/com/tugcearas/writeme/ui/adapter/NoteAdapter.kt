package com.tugcearas.writeme.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tugcearas.writeme.data.room.model.NoteEntity
import com.tugcearas.writeme.databinding.HomeItemBinding
import com.tugcearas.writeme.ui.fragment.HomeFragmentDirections

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var noteList = arrayListOf<NoteEntity>()

    fun searchFiltering(filteredList: List<NoteEntity>) {
        noteList.clear()
        noteList.addAll(filteredList)
        notifyItemRangeChanged(0, filteredList.size)
    }

    class NoteViewHolder(val binding: HomeItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(HomeItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val data = noteList[position]
        holder.binding.cardTitle.text = data.title
        holder.binding.cardSubtitle.text = data.subtitle
        holder.binding.cardDate.text = data.date

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int = noteList.size
}