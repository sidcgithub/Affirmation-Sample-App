package com.example.a30daysaffirmation.core.network.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)