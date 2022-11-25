package com.example.data.remote.request.lost

import com.example.domain.param.lost.LostParam
import com.google.gson.annotations.SerializedName

data class LostRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("isSafe")
    val isSafe: Boolean,
    @SerializedName("place")
    val place: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
)

fun LostParam.toRequest() = LostRequest(
    title = title,
    description = description,
    category = category,
    tags = tags,
    isSafe = isSafe,
    place = place,
    latitude = latitude,
    longitude = longitude
)