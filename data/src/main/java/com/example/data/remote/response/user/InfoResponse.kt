package com.example.data.remote.response.user

import com.example.domain.entity.user.InfoEntity
import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("imageUrl")
    val imageUrl: String
)

fun InfoResponse.toEntity() = InfoEntity(
    id = id,
    userName = userName,
    address = address,
    phoneNumber = phoneNumber,
    imageUrl = imageUrl
)