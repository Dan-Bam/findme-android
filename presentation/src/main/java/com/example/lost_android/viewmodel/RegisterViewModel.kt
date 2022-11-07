package com.example.lost_android.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.param.auth.SignUpParam
import com.example.domain.usecase.auth.SignUpUseCase
import com.example.lost_android.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _registerData = SingleLiveEvent<SignUpParam>()
    val registerData get() = _registerData

    fun setPhone(phone: String) {
        registerData.value = SignUpParam("", "", "", "", "")
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

    fun signUp() {
        viewModelScope.launch {
            kotlin.runCatching {
                signUpUseCase.execute(registerData.value!!)
            }.onSuccess {
            }.onFailure {

            }
        }
    }
}