package com.example.lost_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.domain.entity.lost.LostEntity
import com.example.lost_android.viewmodel.HomeViewModel
import com.example.lost_android.viewmodel.RegisterViewModel
import com.example.presentation.databinding.ItemLostBinding
import com.example.presentation.databinding.ItemTagBinding
import com.example.presentation.databinding.ItemTagEntryBinding

class EntryTagAdapter(private val callback: (String) -> Unit) :
    ListAdapter<String, EntryTagAdapter.EntryTagViewHolder>(diffUtil) {
    inner class EntryTagViewHolder(
        private val binding: ItemTagEntryBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, itemClick: (String) -> Unit) = binding.apply {
            tagTxt.text = item
            deleteBtn.setOnClickListener { itemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryTagViewHolder {
        return EntryTagViewHolder(
            ItemTagEntryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EntryTagViewHolder, position: Int) {
        holder.bind(currentList[position], callback)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}