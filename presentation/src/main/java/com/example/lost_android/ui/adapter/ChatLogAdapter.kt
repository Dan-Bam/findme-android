package com.example.lost_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.chat.ChatLogEntity
import com.example.presentation.databinding.ItemMyChatBinding
import com.example.presentation.databinding.ItemYourChatBinding

class ChatLogAdapter : ListAdapter<ChatLogEntity, RecyclerView.ViewHolder>(diffUtil) {
    class MyChatViewHolder(val binding: ItemMyChatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatLogEntity) {
            binding.chatLog = item
        }
    }

    class YourChatViewHolder(val binding: ItemYourChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatLogEntity) {
            binding.chatLog = item
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isMine) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            return MyChatViewHolder(
                ItemMyChatBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return YourChatViewHolder(
                ItemYourChatBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = getItem(position)
        if (current.isMine) {
            (holder as MyChatViewHolder).bind(current)
        } else {
            (holder as YourChatViewHolder).bind(current)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ChatLogEntity>() {
            override fun areItemsTheSame(oldItem: ChatLogEntity, newItem: ChatLogEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ChatLogEntity,
                newItem: ChatLogEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}