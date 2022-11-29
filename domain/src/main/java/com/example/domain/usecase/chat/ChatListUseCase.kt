package com.example.domain.usecase.chat

import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class ChatListUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend fun execute() =
        chatRepository.chatList()
}