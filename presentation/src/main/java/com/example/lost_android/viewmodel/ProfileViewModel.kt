package com.example.lost_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.user.InfoEntity
import com.example.domain.usecase.user.MyInfoUseCase
import com.example.lost_android.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val myInfoUseCase: MyInfoUseCase
): ViewModel() {
    private val _info = SingleLiveEvent<InfoEntity>()
    val info: MutableLiveData<InfoEntity> get() = _info

    fun getInfo() = viewModelScope.launch {
        kotlin.runCatching {
            myInfoUseCase.execute()
        }.onSuccess {
            _info.value = it
        }.onFailure {

        }
    }
}