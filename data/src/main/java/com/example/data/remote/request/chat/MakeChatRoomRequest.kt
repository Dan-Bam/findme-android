package com.example.data.remote.request.chat

import com.example.domain.param.chat.MakeChatRoomParam
import com.google.gson.annotations.SerializedName

data class MakeChatRoomRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val image: String
)

fun MakeChatRoomParam.toRequest() = MakeChatRoomRequest(
    id = id,
    image = image
)