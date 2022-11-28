package com.example.lost_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.user.InfoEntity
import com.example.domain.entity.user.MyEntryEntity
import com.example.domain.usecase.user.MyFoundUseCase
import com.example.domain.usecase.user.MyInfoUseCase
import com.example.domain.usecase.user.MyLostUseCase
import com.example.lost_android.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val myInfoUseCase: MyInfoUseCase,
    private val myLostUseCase: MyLostUseCase,
    private val myFoundUseCase: MyFoundUseCase
): ViewModel() {
    private val _info = SingleLiveEvent<InfoEntity>()
    val info: MutableLiveData<InfoEntity> get() = _info
    private val _myEntry = SingleLiveEvent<List<MyEntryEntity>>()
    val myEntry: MutableLiveData<List<MyEntryEntity>> get() = _myEntry

    fun getInfo() = viewModelScope.launch {
        kotlin.runCatching {
            myInfoUseCase.execute()
        }.onSuccess {
            _info.value = it
            myLost()
        }.onFailure {

        }
    }

    fun myLost() = viewModelScope.launch {
        kotlin.runCatching {
            myLostUseCase.execute()
        }.onSuccess {
            _myEntry.value = it
        }.onFailure {

        }
    }

    fun myFound() = viewModelScope.launch {
        kotlin.runCatching {
            myFoundUseCase.execute()
        }.onSuccess {
            _myEntry.value = it
        }.onFailure {

        }
    }
}