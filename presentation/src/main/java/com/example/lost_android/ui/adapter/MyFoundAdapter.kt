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

class MyFoundAdapter(private val context: Context, private val callback: (MyFoundEntity) -> Unit) :
    ListAdapter<MyFoundEntity, MyFoundAdapter.MyFoundViewHolder>(diffUtil) {
    inner class MyFoundViewHolder(private val binding: ItemMyFoundBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyFoundEntity, context: Context) = binding.apply {
            binding.entryItem = item
            val height = (context.resources.displayMetrics.heightPixels * 0.2).toInt()
            binding.lostImgHolder.layoutParams =
                ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
            binding.lostImg.load(item.lostImages[0])
            binding.lostLayout.setOnClickListener { callback(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFoundViewHolder {
        return MyFoundViewHolder(
            ItemMyFoundBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyFoundViewHolder, position: Int) {
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