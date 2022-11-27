package com.example.lost_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.lost.LostEntity
import com.example.domain.usecase.lost.FindCategoryUseCase
import com.example.lost_android.util.SingleLiveEvent
import com.example.lost_android.util.removeDot
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val findCategoryUseCase: FindCategoryUseCase
) : ViewModel() {
    private val _areaList = SingleLiveEvent<List<Area>>()
    val areaList: MutableLiveData<List<Area>> get() = _areaList
    private val _currentArea = SingleLiveEvent<Area>()
    val currentArea: MutableLiveData<Area> get() = _currentArea
    private val _currentCategory = SingleLiveEvent<String>()
    val currentCategory: MutableLiveData<String> get() = _currentCategory
    private val _lostList = SingleLiveEvent<List<LostEntity>>()
    val lostList: MutableLiveData<List<LostEntity>> get() = _lostList

    fun searchArea(area: String) = viewModelScope.launch {
        kotlin.runCatching {
            val url =
                "https://api.vworld.kr/req/data?service=data&request=GetFeature&data=LT_C_ADEMD_INFO&key=F48905AB-F5F9-36F1-93AA-85933021A8FB&attrFilter=emd_kor_nm:like:$area"
            val client = OkHttpClient()
            val getArea = Request.Builder()
                .url(url)
                .build()
            client.newCall(getArea).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    _areaList.postValue(listOf())
                    val jsonParser = JsonParser()
                    val responseText = response.body()?.string()
                    val status = ((jsonParser.parse(
                        responseText
                    ) as JsonObject)["response"] as JsonObject)["status"].toString().removeDot()
                    if (status == "OK") {
                        var result = ((((jsonParser.parse(
                            responseText
                        ) as JsonObject)["response"] as JsonObject)["result"] as JsonObject)["featureCollection"] as JsonObject)["features"] as JsonArray
                        result.forEach {
                            it as JsonObject
                            _areaList.postValue(
                                _areaList.value?.plus(
                                    Area(
                                        (it["properties"] as JsonObject)["full_nm"].toString().removeDot(),
                                        (it["properties"] as JsonObject)["emd_kor_nm"].toString().removeDot()
                                    )
                                )
                            )
                        }
                    }
                }
            })
        }
    }

    fun search() = viewModelScope.launch {
        kotlin.runCatching {
            val category = when (_currentCategory.value) {
                "전체보기" -> null
                "핸드폰" -> "phone"
                "노트북" -> "labtop"
                "패드" -> "pad"
                "웨어러블" -> "wearable"
                "이어폰" -> "earphone"
                "반지" -> "ring"
                "팔찌" -> "brace"
                "목걸이" -> "necklace"
                "시계" -> "watch"
                "겉옷" -> "jacket"
                "가방" -> "bag"
                "지갑" -> "wallet"
                else -> null
            }
            findCategoryUseCase.execute(category, _currentArea.value?.fullName ?: "충주")
        }.onSuccess {
            _lostList.value = it
        }.onFailure {
            println("안녕 ${it}")
        }
    }

    fun setArea(area: Area) {
        _currentArea.value = area
    }

    fun setCategory(category: String) {
        _currentCategory.value = category
    }

    data class Area(
        val fullName: String,
        val areaName: String
    )
}