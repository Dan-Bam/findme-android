package com.example.data.repository

import com.example.data.remote.datasource.RemoteChatDataSource
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatDataSource: RemoteChatDataSource
) {
}