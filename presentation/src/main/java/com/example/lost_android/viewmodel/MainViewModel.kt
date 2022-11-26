package com.example.lost_android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.lost.FindAllUseCase
import com.example.lost_android.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
): ViewModel() {
    private val _currentTitle = SingleLiveEvent<String>()
    val currentTitle: MutableLiveData<String> get() = _currentTitle

    fun setTitle(title: String) {
        _currentTitle.value = title
    }
}