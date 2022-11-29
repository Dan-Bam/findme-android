package com.example.domain.repository

import com.example.domain.entity.chat.ChatListEntity
import com.example.domain.entity.chat.ChatLogEntity
import com.example.domain.param.chat.MakeChatRoomParam

interface ChatRepository {
    suspend fun chatList(): List<ChatListEntity>
    suspend fun makeChatRoom(makeChatRoomParam: MakeChatRoomParam)
    suspend fun chatLog(roomId: String): List<ChatLogEntity>
}