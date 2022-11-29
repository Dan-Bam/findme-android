package com.example.data.remote.response.chat

import com.example.domain.entity.chat.ChatLogEntity
import com.google.gson.annotations.SerializedName

data class ChatLogResponse(
    @SerializedName("roomId")
    val id: String,
    @SerializedName("isMine")
    val isMine: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("sentAt")
    val sentAt: String
)

fun ChatLogResponse.toEntity() = ChatLogEntity(
    id = id,
    isMine = isMine,
    message = message,
    sentAt = sentAt
)