package com.example.data.remote.request.auth

import com.example.domain.param.auth.LoginParam
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String
)

fun LoginParam.toRequest() = LoginRequest(
    id = id,
    password = password
)