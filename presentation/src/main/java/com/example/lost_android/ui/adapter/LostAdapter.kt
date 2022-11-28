package com.example.lost_android.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.domain.entity.lost.LostEntity
import com.example.presentation.databinding.ItemLostBinding

class LostAdapter(private val context: Context, private val callback: (LostEntity) -> Unit) :
    ListAdapter<LostEntity, LostAdapter.LostViewHolder>(diffUtil) {
    inner class LostViewHolder(private val binding: ItemLostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LostEntity, context: Context) = binding.apply {
            binding.lostItem = item
            val height = (context.resources.displayMetrics.heightPixels * 0.2).toInt()
            binding.lostImgHolder.layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
            binding.lostImg.load(item.lostImages[0])
            binding.lostLayout.setOnClickListener { callback(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LostViewHolder {
        return LostViewHolder(
            ItemLostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LostViewHolder, position: Int) {
        holder.bind(currentList[position], context)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<LostEntity>() {
            override fun areItemsTheSame(oldItem: LostEntity, newItem: LostEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: LostEntity, newItem: LostEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}