package com.example.domain.usecase.chat

import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class ChatLogUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend fun execute(roomId: String) =
        chatRepository.chatLog(roomId)
}