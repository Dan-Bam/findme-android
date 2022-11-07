package com.example.domain.param.auth

import com.google.gson.annotations.SerializedName

data class RegisterParam(
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
