package com.example.data.remote.datasource

import com.example.data.remote.network.ChatAPI
import javax.inject.Inject

class RemoteChatDataSourceImpl @Inject constructor(
    private val chatAPI: ChatAPI
) {
}