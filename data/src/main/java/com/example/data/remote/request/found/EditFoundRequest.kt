package com.example.data.remote.request.found

import com.example.domain.param.found.EditFoundParam
import com.google.gson.annotations.SerializedName

data class EditFoundRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("place")
    val place: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
)

fun EditFoundParam.toRequest() = EditFoundRequest(
    title = title,
    description = description,
    tags = tags,
    place = place,
    latitude = latitude,
    longitude = longitude
)
