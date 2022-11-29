package com.example.domain.entity.chat

data class ChatLogEntity(
    val id: String,
    val isMine: Boolean,
    val message: String,
    val sentAt: String
)