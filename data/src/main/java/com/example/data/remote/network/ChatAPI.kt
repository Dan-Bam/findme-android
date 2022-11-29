package com.example.data.remote.network

import com.example.data.remote.request.chat.MakeChatRoomRequest
import com.example.data.remote.response.chat.ChatListResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatAPI {
    @GET("chat")
    suspend fun chatList(): List<ChatListResponse>
    @POST
    suspend fun makeChatRoom(makeChatRoomRequest: MakeChatRoomRequest)
}