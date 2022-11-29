package com.example.lost_android.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.domain.entity.user.MyLostEntity
import com.example.presentation.databinding.ItemMyLostBinding

class MyLostAdapter(private val context: Context, private val callback: (MyLostEntity) -> Unit) :
    ListAdapter<MyLostEntity, MyLostAdapter.MyLostViewHolder>(diffUtil) {
    inner class MyLostViewHolder(private val binding: ItemMyLostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyLostEntity, context: Context) = binding.apply {
            binding.entryItem = item
            val height = (context.resources.displayMetrics.heightPixels * 0.2).toInt()
            binding.lostImgHolder.layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
            binding.lostImg.load(item.lostImages[0])
            binding.lostLayout.setOnClickListener { callback(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLostViewHolder {
        return MyLostViewHolder(
            ItemMyLostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyLostViewHolder, position: Int) {
        holder.bind(currentList[position], context)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MyLostEntity>() {
            override fun areItemsTheSame(oldItem: MyLostEntity, newItem: MyLostEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MyLostEntity,
                newItem: MyLostEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}