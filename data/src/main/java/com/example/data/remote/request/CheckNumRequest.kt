package com.example.data.remote.request

import com.example.domain.param.auth.CheckNumParam
import com.google.gson.annotations.SerializedName

data class CheckNumRequest(
    @SerializedName("phoneNumber")
    val phone: String,
    @SerializedName("authKey")
    val number: String
)

fun CheckNumParam.toRequest() = CheckNumRequest(
    phone = phone,
    number = number
)
