package com.example.lost_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.param.auth.CheckNumParam
import com.example.domain.param.auth.SendNumParam
import com.example.domain.param.auth.SignUpParam
import com.example.domain.usecase.auth.CheckNumUseCase
import com.example.domain.usecase.auth.SendNumUseCase
import com.example.domain.usecase.auth.SignUpUseCase
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
class RegisterViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val sendNumUseCase: SendNumUseCase,
    private val checkNumUseCase: CheckNumUseCase
) : ViewModel() {
    private val _registerData = SingleLiveEvent<SignUpParam>()
    private val _certify = SingleLiveEvent<Boolean>()
    private val _addressInfo = SingleLiveEvent<List<Address>>()
    val registerData: MutableLiveData<SignUpParam> get() = _registerData
    val certify: MutableLiveData<Boolean> get() = _certify
    val addressInfo: MutableLiveData<List<Address>> get() = _addressInfo

    fun setPhone(phone: String) {
        _registerData.value = SignUpParam("", "", "", "", "")
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
                signUpUseCase.execute(_registerData.value!!)
            }.onSuccess {
            }.onFailure {
            }
        }
    }

    fun sendNum() = viewModelScope.launch {
        kotlin.runCatching {
            sendNumUseCase.execute(SendNumParam(_registerData.value!!.phone))
        }.onSuccess {

        }.onFailure {
        }
    }

    fun checkNum(number: String) = viewModelScope.launch {
        kotlin.runCatching {
            checkNumUseCase.execute(CheckNumParam(_registerData.value!!.phone, number))
        }.onSuccess {
            _certify.value = true
        }.onFailure {
            _certify.value = false
        }
    }

    fun getAddress(address: String) = viewModelScope.launch {
        kotlin.runCatching {
            val url =
                "https://business.juso.go.kr/addrlink/addrLinkApi.do?keyword=$address&confmKey=devU01TX0FVVEgyMDIyMTEwNzE4MDQ1MjExMzE5NTY=&resultType=json"
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
                        var resultList = listOf<Address>()
                        (result as JsonArray).forEach {
                            it as JsonObject
                            resultList = resultList.plus(
                                Address(
                                    it["jibunAddr"].toString().removeDot(),
                                    it["roadAddr"].toString().removeDot()
                                )
                            )
                        }
                        _addressInfo.postValue(resultList)
                    }
                }
            })
        }.onSuccess {
        }.onFailure {
        }
    }

    data class Address(
        val address: String,
        val road: String
    )
}