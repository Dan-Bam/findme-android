package com.example.data.remote.request

import com.example.domain.param.auth.SendNumParam
import com.google.gson.annotations.SerializedName

data class SendNumRequest(
    @SerializedName("phoneNumber")
    val phone: String
)

fun SendNumParam.toRequest() = SendNumRequest(
    phone = phone
)
