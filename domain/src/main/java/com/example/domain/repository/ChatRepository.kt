package com.example.domain.repository

import com.example.domain.entity.chat.ChatListEntity

interface ChatRepository {
    suspend fun chatList(): List<ChatListEntity>
}