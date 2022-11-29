package com.example.data.remote.network

import com.example.data.remote.request.chat.MakeChatRoomRequest
import com.example.data.remote.response.chat.ChatListResponse
import com.example.data.remote.response.chat.ChatLogResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatAPI {
    @GET("chat")
    suspend fun chatList(): List<ChatListResponse>
    @POST("chat")
    suspend fun makeChatRoom(makeChatRoomRequest: MakeChatRoomRequest)
    @GET("chat/{roomId}")
    suspend fun chatLog(
        @Path("roomId") roomId: String
    ): List<ChatLogResponse>
}