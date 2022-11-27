package com.example.lost_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.lost.LostEntity
import com.example.domain.usecase.lost.DetailLostUseCase
import com.example.lost_android.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailLostUseCase: DetailLostUseCase
): ViewModel() {
    private val _lostData = SingleLiveEvent<LostEntity>()
    val lostData: MutableLiveData<LostEntity> get() = _lostData
    fun getDetail(lostId: String) = viewModelScope.launch {
        kotlin.runCatching {
            detailLostUseCase.execute(lostId)
        }.onSuccess {
            _lostData.value = it
        }
    }
}