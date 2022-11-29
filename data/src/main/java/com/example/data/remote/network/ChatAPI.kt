package com.example.data.remote.network

import com.example.data.remote.response.chat.ChatListResponse
import retrofit2.http.GET

interface ChatAPI {
    @GET("chat")
    suspend fun chatList(): List<ChatListResponse>
}