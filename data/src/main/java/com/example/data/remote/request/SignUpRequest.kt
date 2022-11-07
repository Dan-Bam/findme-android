package com.example.data.remote.request

import com.example.domain.param.auth.SignUpParam
import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("id")
    var id: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("phoneNumber")
    var phone: String,
    @SerializedName("userName")
    var name: String,
    @SerializedName("address")
    var address: String
)

fun SignUpParam.toRequest() = SignUpRequest(
    id = id,
    password = password,
    phone = phone,
    name = name,
    address = address
)
