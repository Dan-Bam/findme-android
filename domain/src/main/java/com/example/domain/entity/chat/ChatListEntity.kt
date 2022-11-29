package com.example.domain.entity.chat

data class ChatListEntity(
    val roomId: String,
    val roomName: String,
    val roomImage: String,
    val lastChat: LastChat
) {
    data class LastChat(
        val lastMsg: String,
        val lastSent: String,
    )
}