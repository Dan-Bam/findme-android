package com.example.data.remote.datasource

import com.example.data.remote.response.chat.ChatListResponse

interface RemoteChatDataSource {
    suspend fun chatList(): List<ChatListResponse>
}