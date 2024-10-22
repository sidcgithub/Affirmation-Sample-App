package com.example.a30daysaffirmation.network.model

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("url")
    val url: String
)
