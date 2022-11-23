package com.example.data.remote.request.lost

import com.example.domain.param.lost.LostParam
import com.google.gson.annotations.SerializedName

data class LostRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("category")
    val category: List<Category>,
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
) {
    data class Category(
        @SerializedName("mainCategory")
        val mainCategory: String,
        @SerializedName("subCategory")
        val subCategory: String
    )
}

fun LostParam.toRequest() = LostRequest(
    title = title,
    description = description,
    category = category.map { it.toRequest() },
    tags = tags,
    isSafe = isSafe,
    place = place,
    latitude = latitude,
    longitude = longitude
)

fun LostParam.Category.toRequest() = LostRequest.Category(
    mainCategory = mainCategory,
    subCategory = subCategory
)