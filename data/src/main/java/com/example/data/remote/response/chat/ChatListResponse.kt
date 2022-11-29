package com.example.data.remote.response.chat

import com.example.domain.entity.chat.ChatListEntity
import com.google.gson.annotations.SerializedName

data class ChatListResponse(
    @SerializedName("roomId")
    val roomId: String,
    @SerializedName("roomImage")
    val roomImage: String,
    @SerializedName("lastChat")
    val lastChat: LastChat
) {
    data class LastChat(
        @SerializedName("lastMessage")
        val lastMsg: String,
        @SerializedName("lastSentAt")
        val lastSent: String,
    )

    fun LastChat.toEntity() = ChatListEntity.LastChat(
        lastMsg = lastMsg,
        lastSent = lastSent
    )
}

fun ChatListResponse.toEntity() = ChatListEntity(
    roomId = roomId,
    roomImage = roomImage,
    lastChat = lastChat.toEntity()
)