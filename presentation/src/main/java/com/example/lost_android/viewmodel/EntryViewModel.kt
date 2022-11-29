package com.example.lost_android.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.param.found.EditFoundParam
import com.example.domain.param.found.FoundParam
import com.example.domain.param.lost.EditLostParam
import com.example.domain.param.lost.LostParam
import com.example.domain.usecase.found.EditFoundUseCase
import com.example.domain.usecase.found.EntryFoundUseCase
import com.example.domain.usecase.lost.EditLostUseCase
import com.example.domain.usecase.lost.EntryLostUseCase
import com.example.lost_android.util.SingleLiveEvent
import com.example.lost_android.util.removeDot
import com.example.lost_android.util.toRequestBody
import com.google.android.gms.maps.model.LatLng
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.File
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val entryLostUseCase: EntryLostUseCase,
    private val entryFoundUseCase: EntryFoundUseCase,
    private val editLostUseCase: EditLostUseCase,
    private val editFoundUseCase: EditFoundUseCase
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
    private val _addressInfo = SingleLiveEvent<List<RegisterViewModel.Address>>()
    val addressInfo: MutableLiveData<List<RegisterViewModel.Address>> get() = _addressInfo
    private val _tags = SingleLiveEvent<List<String>>()
    val tags: MutableLiveData<List<String>> get() = _tags

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

    fun setTags(tag: String) {
        if (_tags.value == null) {
            _tags.value = listOf(tag)
        } else {
            if (!_tags.value!!.contains(tag)) {
                _tags.value = _tags.value!!.plus(tag)
            }
        }
    }

    fun deleteTags(tag: String) {
        _tags.value = _tags.value!!.filter { it != tag }
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
                _tags.value!!,
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

    fun entryFound(file: File) = viewModelScope.launch {
        kotlin.runCatching {
            _isEntry.value = false
            val params = FoundParam(
                _params.value!!["title"]!!,
                _params.value!!["description"]!!,
                _category.value!!,
                _tags.value!!,
                _currentAddress.value!!.address,
                _currentAddress.value!!.latLng.latitude.toString(),
                _currentAddress.value!!.latLng.longitude.toString()
            )
            entryFoundUseCase.execute(params, file.toRequestBody())
        }.onSuccess {
            _isEntry.value = true
        }.onFailure {

        }
    }

    fun editLost(lostId: String, file: File?) = viewModelScope.launch {
        kotlin.runCatching {
            _isEntry.value = false
            val params = EditLostParam(
                _params.value!!["title"]!!,
                _params.value!!["description"]!!,
                listOf("핸드폰"),
                false,
                _currentAddress.value!!.address,
                _currentAddress.value!!.latLng.latitude.toString(),
                _currentAddress.value!!.latLng.longitude.toString()
            )
            editLostUseCase.execute(lostId, params, file?.toRequestBody())
        }.onSuccess {
            _isEntry.value = true
        }.onFailure {
            println("안녕 ${it}")
        }
    }

    fun editFound(foundId: String, file: File?) = viewModelScope.launch {
        kotlin.runCatching {
            _isEntry.value = false
            val params = EditFoundParam(
                _params.value!!["title"]!!,
                _params.value!!["description"]!!,
                listOf("핸드폰"),
                _currentAddress.value!!.address,
                _currentAddress.value!!.latLng.latitude.toString(),
                _currentAddress.value!!.latLng.longitude.toString()
            )
            editFoundUseCase.execute(foundId, params, file?.toRequestBody())
        }.onSuccess {
            _isEntry.value = true
        }.onFailure {
            println("안녕 ${it}")
        }
    }

    fun getAddress(latLng: LatLng) = viewModelScope.launch {
        kotlin.runCatching {
            val url =
                "https://api.vworld.kr/req/address?service=address&request=getAddress&version=2.0&crs=epsg:4326&point=${latLng.longitude},${latLng.latitude}&type=both&zipcode=true&simple=false&key=F48905AB-F5F9-36F1-93AA-85933021A8FB"
            val client = OkHttpClient()
            val getArea = Request.Builder()
                .url(url)
                .build()
            client.newCall(getArea).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val jsonParser = JsonParser()
                    val responseText = response.body()?.string()
                    val status = ((jsonParser.parse(
                        responseText
                    ) as JsonObject)["response"] as JsonObject)["status"].toString().removeDot()
                    if (status == "OK") {
                        var result = ((((jsonParser.parse(
                            responseText
                        ) as JsonObject)["response"] as JsonObject)["result"] as JsonArray))
                        _currentAddress.postValue(
                            Address(
                                (result[0] as JsonObject)["text"].toString().removeDot(),
                                latLng
                            )
                        )
                    }
                }
            })
        }
    }

    fun getLanLng(address: String) = viewModelScope.launch {
        kotlin.runCatching {
            val url =
                "https://api.vworld.kr/req/address?service=address&request=getcoord&version=2.0&crs=epsg:4326&address=$address&refine=true&simple=false&type=parcel&key=F48905AB-F5F9-36F1-93AA-85933021A8FB"
            val client = OkHttpClient()
            val getLatLng = Request.Builder()
                .url(url)
                .build()
            client.newCall(getLatLng).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val jsonParser = JsonParser()
                    val response1 = (jsonParser.parse(
                        response.body()?.string()
                    ) as JsonObject)["response"] as JsonObject
                    if (response1.toString() != "null") {
                        val refined = response1["refined"] as JsonObject
                        val result = (response1["result"] as JsonObject)["point"] as JsonObject
                        _currentAddress.postValue(
                            Address(
                                refined["text"].toString().removeDot(),
                                LatLng(
                                    result["y"].toString().removeDot().toDouble(),
                                    result["x"].toString().removeDot().toDouble()
                                )
                            )
                        )
                    }
                }
            })
        }
    }

    fun getAddress(address: String) = viewModelScope.launch {
        kotlin.runCatching {
            val url =
                "https://business.juso.go.kr/addrlink/addrLinkApi.do?keyword=$address&confmKey=devU01TX0FVVEgyMDIyMTEwNzE4MDQ1MjExMzE5NTY=&resultType=json&countPerPage=100"
            val client = OkHttpClient()
            val getAddress = Request.Builder()
                .url(url)
                .build()
            client.newCall(getAddress).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val jsonParser = JsonParser()
                    val result = ((jsonParser.parse(
                        response.body()?.string()
                    ) as JsonObject)["results"] as JsonObject)["juso"]
                    if (result.toString() != "null") {
                        var resultList = listOf<RegisterViewModel.Address>()
                        (result as JsonArray).forEach {
                            it as JsonObject
                            resultList = resultList.plus(
                                RegisterViewModel.Address(
                                    it["bdNm"].toString().removeDot(),
                                    it["jibunAddr"].toString().removeDot()
                                )
                            )
                        }
                        _addressInfo.postValue(resultList)
                    }
                }
            })
        }
    }
}