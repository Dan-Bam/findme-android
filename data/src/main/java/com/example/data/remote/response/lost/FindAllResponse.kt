package com.example.data.remote.response.lost

import com.example.domain.entity.lost.FindAllEntity
import com.google.gson.annotations.SerializedName

data class FindAllResponse(
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
    val category: List<Category>,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("isSafe")
    val isSafe: Boolean,
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

    fun Category.toEntity() = FindAllEntity.Category(
        mainCategory = mainCategory,
        subCategory = subCategory
    )
}

fun FindAllResponse.toEntity() = FindAllEntity(
    id = id,
    title = title,
    description = description,
    place = place,
    lostImages = lostImages,
    category = category.map { it.toEntity() },
    tags = tags,
    isSafe = isSafe,
    latitude = latitude,
    longitude = longitude
)