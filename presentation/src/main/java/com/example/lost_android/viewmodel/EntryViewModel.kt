package com.example.lost_android.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.param.lost.LostParam
import com.example.domain.usecase.lost.EntryLostUseCase
import com.example.lost_android.util.SingleLiveEvent
import com.example.lost_android.util.toRequestBody
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val entryLostUseCase: EntryLostUseCase
) : ViewModel() {
    private val _title = SingleLiveEvent<String>()
    val title: MutableLiveData<String> get() = _title
    private val _currentAddress = SingleLiveEvent<Address>()
    val currentAddress: MutableLiveData<Address> get() = _currentAddress
    private val _currentUri = SingleLiveEvent<Uri>()
    val currentUri: MutableLiveData<Uri> get() = _currentUri
    private val _category = SingleLiveEvent<String>()
    val category: MutableLiveData<String> get() = _category
    private val _params = SingleLiveEvent<HashMap<String, String>>()
    val params: MutableLiveData<HashMap<String, String>> get() = _params
    private val _isEntry = SingleLiveEvent<Boolean>()
    val isEntry: MutableLiveData<Boolean> get() = _isEntry

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setAddress(address: Address) {
        _currentAddress.value = address
    }

    fun setCategory(category: String) {
        _category.value = category
    }

    fun setUri(uri: Uri) {
        _currentUri.value = uri
    }

    data class Address(
        val address: String,
        val latLng: LatLng
    )

    fun saveData(title: String, description: String) {
        _params.value = HashMap()
        _params.value!!["title"] = title
        _params.value!!["description"] = description
    }

    fun entryLost(file: File) = viewModelScope.launch {
        kotlin.runCatching {
            _isEntry.value = false
            val params = LostParam(
                _params.value!!["title"]!!,
                _params.value!!["description"]!!,
                _category.value!!,
                listOf("핸드폰"),
                false,
                _currentAddress.value!!.address,
                _currentAddress.value!!.latLng.latitude.toString(),
                _currentAddress.value!!.latLng.longitude.toString(),
            )
            entryLostUseCase.execute(params, file.toRequestBody())
        }.onSuccess {
            _isEntry.value = true
        }.onFailure {
            println("안녕 $it")
        }
    }
}