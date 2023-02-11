package com.example.lost_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.chat.ChatListEntity
import com.example.domain.entity.chat.ChatLogEntity
import com.example.domain.usecase.chat.ChatListUseCase
import com.example.domain.usecase.chat.ChatLogUseCase
import com.example.lost_android.util.SingleLiveEvent
import com.example.lost_android.util.HttpSocketListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatListUseCase: ChatListUseCase,
    private val chatLogUseCase: ChatLogUseCase,
) : ViewModel() {
    private val _chatList = SingleLiveEvent<List<ChatListEntity>>()
    val chatList: MutableLiveData<List<ChatListEntity>> get() = _chatList
    private val _chatLog = SingleLiveEvent<List<ChatLogEntity>>()
    val chatLog: MutableLiveData<List<ChatLogEntity>> get() = _chatLog
    private var client: OkHttpClient = OkHttpClient()
    private lateinit var request: Request
    private lateinit var socket: WebSocket

//    private fun connect(accessToken: String) = viewModelScope.launch {
//        client = OkHttpClient()
//        request = Request.Builder().url("ws://3.35.190.72:8080/ws/chat")
//            .addHeader("Authorization", accessToken).build()
//        socket = client.newWebSocket(request, HttpSocketListener().listener)
//    }


//    fun disconnect() = viewModelScope.launch {
//        client.dispatcher().executorService().shutdown()
//    }

    fun sendMessage(roomId: String, message: String) = viewModelScope.launch {
//        socket.send("{\"roomId\":${roomId.toInt()}, \"message\": \"$message\"}")
    }

    fun chatList() = viewModelScope.launch {
        kotlin.runCatching {
            chatListUseCase.execute()
        }.onSuccess {
            _chatList.value = it
        }.onFailure {
        }
    }

    fun chatLog(roomId: String, accessToken: String) = viewModelScope.launch {
        kotlin.runCatching {
            chatLogUseCase.execute(roomId)
        }.onSuccess {
            _chatLog.value = it
//            connect(accessToken)
        }.onFailure {
        }
    }
}