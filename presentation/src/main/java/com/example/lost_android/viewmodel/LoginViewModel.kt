package com.example.lost_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.param.auth.LoginParam
import com.example.domain.usecase.auth.LoginUseCase
import com.example.lost_android.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {
    private val _state = SingleLiveEvent<String>()
    val state: MutableLiveData<String> get() = _state
    fun login(id: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                loginUseCase.execute(LoginParam(id, password))
            }.onSuccess {
                _state.value = "SUCCESS"
            }.onFailure {
                _state.value = "FAIL"
            }
        }
    }
}