package com.example.data.remote.response.lost

import com.example.domain.entity.lost.LostEntity
import com.google.gson.annotations.SerializedName

data class LostResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("place")
    val place: String,
    @SerializedName("lostImages")
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

fun LostResponse.toEntity() = LostEntity(
    id = id,
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