package com.example.lost_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.found.FoundEntity
import com.example.domain.entity.lost.LostEntity
import com.example.domain.usecase.found.DeleteFoundUseCase
import com.example.domain.usecase.found.DetailFoundUseCase
import com.example.domain.usecase.lost.DeleteLostUseCase
import com.example.domain.usecase.lost.DetailLostUseCase
import com.example.lost_android.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailLostUseCase: DetailLostUseCase,
    private val deleteLostUseCase: DeleteLostUseCase,
    private val detailFoundUseCase: DetailFoundUseCase,
    private val deleteFoundUseCase: DeleteFoundUseCase
) : ViewModel() {
    private val _lostData = SingleLiveEvent<LostEntity>()
    val lostData: MutableLiveData<LostEntity> get() = _lostData
    private val _foundData = SingleLiveEvent<FoundEntity>()
    val foundData: MutableLiveData<FoundEntity> get() = _foundData

    fun detailLost(lostId: String) = viewModelScope.launch {
        kotlin.runCatching {
            detailLostUseCase.execute(lostId)
        }.onSuccess {
            _lostData.value = it
        }
    }

    fun detailFound(foundId: String) = viewModelScope.launch {
        kotlin.runCatching {
            detailFoundUseCase.execute(foundId)
        }.onSuccess {
            _foundData.value = it
        }
    }

    fun deleteLost() = viewModelScope.launch {
        kotlin.runCatching {
            deleteLostUseCase.execute(_lostData.value!!.id)
        }.onSuccess {
        }.onFailure {

        }
    }

    fun deleteFound() = viewModelScope.launch {
        kotlin.runCatching {
            deleteFoundUseCase.execute(_foundData.value!!.id)
        }.onSuccess {

        }.onFailure {

        }
    }
}