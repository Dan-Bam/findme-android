package com.example.lost_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.chat.ChatListEntity
import com.example.domain.entity.chat.ChatLogEntity
import com.example.domain.usecase.chat.ChatListUseCase
import com.example.domain.usecase.chat.ChatLogUseCase
import com.example.lost_android.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatListUseCase: ChatListUseCase,
    private val chatLogUseCase: ChatLogUseCase
): ViewModel() {
    private val _chatList = SingleLiveEvent<List<ChatListEntity>>()
    val chatList: MutableLiveData<List<ChatListEntity>> get() = _chatList
    private val _chatLog = SingleLiveEvent<List<ChatLogEntity>>()
    val chatLog: MutableLiveData<List<ChatLogEntity>> get() = _chatLog

    fun chatList() = viewModelScope.launch {
        kotlin.runCatching {
            chatListUseCase.execute()
        }.onSuccess {
            _chatList.value = it
        }.onFailure {
        }
    }

    fun chatLog(roomId: String) = viewModelScope.launch {
        kotlin.runCatching {
            chatLogUseCase.execute(roomId)
        }.onSuccess {
            _chatLog.value = it
        }.onFailure {
        }
    }
}