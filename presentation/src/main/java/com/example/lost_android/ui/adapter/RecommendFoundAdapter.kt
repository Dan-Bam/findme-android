package com.example.lost_android.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.domain.entity.user.MyFoundEntity
import com.example.presentation.databinding.ItemMyFoundBinding
import com.example.presentation.databinding.ItemRecommendFoundBinding

class RecommendFoundAdapter(private val context: Context, private val callback: (MyFoundEntity) -> Unit) :
    ListAdapter<MyFoundEntity, RecommendFoundAdapter.RecommendFoundViewHolder>(diffUtil) {
    inner class RecommendFoundViewHolder(private val binding: ItemRecommendFoundBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyFoundEntity, context: Context) = binding.apply {
            binding.recommendImg.load(item.lostImages[0])
            binding.recommendImg.setOnClickListener { callback(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendFoundViewHolder {
        return RecommendFoundViewHolder(
            ItemRecommendFoundBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecommendFoundViewHolder, position: Int) {
        holder.bind(currentList[position], context)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MyFoundEntity>() {
            override fun areItemsTheSame(oldItem: MyFoundEntity, newItem: MyFoundEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MyFoundEntity,
                newItem: MyFoundEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}