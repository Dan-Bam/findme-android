package com.example.data.remote.datasource

import com.example.data.remote.request.chat.MakeChatRoomRequest
import com.example.data.remote.response.chat.ChatListResponse
import com.example.data.remote.response.chat.ChatLogResponse

interface RemoteChatDataSource {
    suspend fun chatList(): List<ChatListResponse>
    suspend fun makeChatRoom(makeChatRoomRequest: MakeChatRoomRequest)
    suspend fun chatLog(roomId: String): List<ChatLogResponse>
}