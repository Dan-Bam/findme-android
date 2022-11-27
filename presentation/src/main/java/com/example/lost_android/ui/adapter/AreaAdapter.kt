package com.example.lost_android.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lost_android.viewmodel.HomeViewModel
import com.example.presentation.databinding.ItemAreaBinding

class AreaAdapter(private val callback: (HomeViewModel.Area) -> Unit) :
    ListAdapter<HomeViewModel.Area, AreaAdapter.AreaViewHolder>(diffUtil) {
    inner class AreaViewHolder(private val binding: ItemAreaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeViewModel.Area) = binding.apply {
            binding.area = item
            binding.selectTxt.setOnClickListener { callback(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        return AreaViewHolder(
            ItemAreaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HomeViewModel.Area>() {
            override fun areItemsTheSame(oldItem: HomeViewModel.Area, newItem: HomeViewModel.Area): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: HomeViewModel.Area, newItem: HomeViewModel.Area): Boolean {
                return oldItem == newItem
            }
        }
    }
}