package com.example.lost_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.chat.ChatListEntity
import com.example.domain.usecase.chat.ChatListUseCase
import com.example.lost_android.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatListUseCase: ChatListUseCase
): ViewModel() {
    private val _chatList = SingleLiveEvent<List<ChatListEntity>>()
    val chatList: MutableLiveData<List<ChatListEntity>> get() = _chatList

    fun chatList() = viewModelScope.launch {
        kotlin.runCatching {
            chatListUseCase.execute()
        }.onSuccess {
            _chatList.value = it
        }.onFailure {
        }
    }
}