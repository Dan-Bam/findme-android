package com.example.data.remote.response.user

import com.example.data.remote.response.found.FoundResponse
import com.example.domain.entity.found.FoundEntity
import com.example.domain.entity.user.MyFoundEntity
import com.google.gson.annotations.SerializedName

data class MyFoundResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: String,
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
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
)

fun MyFoundResponse.toEntity() = MyFoundEntity(
    id = id,
    userId = userId,
    title = title,
    description = description,
    place = place,
    lostImages = lostImages,
    category = category,
    tags = tags,
    latitude = latitude,
    longitude = longitude
)