package com.example.data.remote.response.user

import com.example.domain.entity.user.MyLostEntity
import com.google.gson.annotations.SerializedName

data class MyLostResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("isMine")
    val isMine: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("place")
    val place: String,
    @SerializedName("imageUrl")
    val lostImages: List<String>,
    @SerializedName("category")
    val category: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("isSafe")
    val isSafe: Boolean,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
)

fun MyLostResponse.toEntity() = MyLostEntity(
    id = id,
    userId = userId,
    isMine = isMine,
    title = title,
    description = description,
    place = place,
    lostImages = lostImages,
    category = category,
    tags = tags,
    isSafe = isSafe,
    latitude = latitude,
    longitude = longitude
)