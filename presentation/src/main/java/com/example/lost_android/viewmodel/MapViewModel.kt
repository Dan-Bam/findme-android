package com.example.lost_android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.lost.LostEntity
import com.example.domain.usecase.lost.FindAllUseCase
import com.example.lost_android.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val findAllUseCase: FindAllUseCase
): ViewModel() {
    private val _mapData = SingleLiveEvent<List<LostEntity>>()
    val mapData: MutableLiveData<List<LostEntity>> get() = _mapData
    fun findAll() = viewModelScope.launch {
        kotlin.runCatching {
            findAllUseCase.execute()
        }.onSuccess {
            _mapData.value = it
        }.onFailure {
            Log.d("안녕", "findAll: ${it}")
        }
    }
}