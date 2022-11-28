package com.example.data.remote.request.user

import com.example.domain.param.user.EditInfoParam
import com.google.gson.annotations.SerializedName

data class EditInfoRequest(
    @SerializedName("userName")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("phoneNumber")
    val phone: String,
)

fun EditInfoParam.toRequest() = EditInfoRequest(
    name = name,
    address = address,
    phone = phone
)