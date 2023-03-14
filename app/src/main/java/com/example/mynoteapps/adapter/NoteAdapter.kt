package com.example.mynoteapps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapps.data.local.entity.NoteEntity
import com.example.mynoteapps.databinding.NoteItemLayoutBinding

class NoteAdapter(var data:List<NoteEntity>):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(binding: NoteItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        var title = binding.titleTv
        var desc = binding.descTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.title.text = data[position].title
        holder.desc.text = data[position].description
    }
}