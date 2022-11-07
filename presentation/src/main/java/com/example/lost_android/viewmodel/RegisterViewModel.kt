package com.example.lost_android.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.param.auth.RegisterParam
import com.example.lost_android.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    private val _registerData = SingleLiveEvent<RegisterParam>()
    val registerData get() = _registerData

    fun setPhone(phone: String) {
        registerData.value = RegisterParam("", "", "", "", "")
        _registerData.value?.phone = phone
    }

    fun setInfo(id: String, pw: String) {
        _registerData.value.apply {
            this?.id = id
            this?.password = pw
        }
    }

    fun setName(name: String) {
        _registerData.value?.name = name
    }

    fun setAddress(address: String) {
        _registerData.value?.address = address
    }
}