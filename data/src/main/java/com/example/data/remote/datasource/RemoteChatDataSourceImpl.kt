package com.example.data.remote.datasource

import com.example.data.remote.network.ChatAPI
import com.example.data.remote.request.chat.MakeChatRoomRequest
import com.example.data.remote.response.chat.ChatListResponse
import com.example.data.util.HttpHandler
import javax.inject.Inject

class RemoteChatDataSourceImpl @Inject constructor(
    private val chatAPI: ChatAPI
): RemoteChatDataSource {
    override suspend fun chatList(): List<ChatListResponse> {
        return HttpHandler<List<ChatListResponse>>()
            .httpRequest { chatAPI.chatList() }
            .sendRequest()
    }

    override suspend fun makeChatRoom(makeChatRoomRequest: MakeChatRoomRequest) {
        return HttpHandler<Unit>()
            .httpRequest { chatAPI.makeChatRoom(makeChatRoomRequest) }
            .sendRequest()
    }
}