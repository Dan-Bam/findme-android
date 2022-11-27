package com.example.lost_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.lost.EntryLostUseCase
import com.example.lost_android.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val entryLostUseCase: EntryLostUseCase
): ViewModel() {
    private val _title = SingleLiveEvent<String>()
    val title: MutableLiveData<String> get() = _title

    fun setTitle(title: String) {
        _title.value = title
    }
}