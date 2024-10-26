package com.example.a30daysaffirmation.core.network.model

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnailUrl")
    val url: String
)
