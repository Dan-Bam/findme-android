package com.example.data.remote.request.found

import com.example.domain.param.found.FoundParam
import com.google.gson.annotations.SerializedName

data class FoundRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("place")
    val place: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
)

fun FoundParam.toRequest() = FoundRequest(
    title = title,
    description = description,
    category = category,
    tags = tags,
    place = place,
    latitude = latitude,
    longitude = longitude
)
