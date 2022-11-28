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
import com.example.domain.entity.user.MyEntryEntity
import com.example.presentation.databinding.ItemEntryBinding
import com.example.presentation.databinding.ItemLostBinding

class EntryAdapter(private val context: Context, private val callback: (MyEntryEntity) -> Unit) :
    ListAdapter<MyEntryEntity, EntryAdapter.EntryViewHolder>(diffUtil) {
    inner class EntryViewHolder(private val binding: ItemEntryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyEntryEntity, context: Context) = binding.apply {
            binding.entryItem = item
            val height = (context.resources.displayMetrics.heightPixels * 0.2).toInt()
            binding.lostImgHolder.layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
            binding.lostImg.load(item.lostImages[0])
            binding.lostLayout.setOnClickListener { callback(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        return EntryViewHolder(
            ItemEntryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.bind(currentList[position], context)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MyEntryEntity>() {
            override fun areItemsTheSame(oldItem: MyEntryEntity, newItem: MyEntryEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MyEntryEntity,
                newItem: MyEntryEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}