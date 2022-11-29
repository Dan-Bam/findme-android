package com.example.lost_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.domain.entity.chat.ChatListEntity
import com.example.presentation.databinding.ItemChatRoomBinding

class ChatRoomAdapter(private val itemClick: (ChatListEntity) -> Unit) : ListAdapter<ChatListEntity, ChatRoomAdapter.ChatRoomViewHolder>(diffUtil) {
    class ChatRoomViewHolder(val binding: ItemChatRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatListEntity, itemClick: (ChatListEntity) -> Unit) {
            binding.chatRoom = item
            binding.chatRoomImg.load(item.roomImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomViewHolder {
        return ChatRoomViewHolder(
            ItemChatRoomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChatRoomViewHolder, position: Int) {
        holder.bind(currentList[position], itemClick)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ChatListEntity>() {
            override fun areItemsTheSame(
                oldItem: ChatListEntity,
                newItem: ChatListEntity
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ChatListEntity,
                newItem: ChatListEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}