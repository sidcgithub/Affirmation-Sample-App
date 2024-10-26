package com.example.a30daysaffirmation.core.network

import com.example.a30daysaffirmation.core.network.model.PhotoResponse
import com.example.a30daysaffirmation.core.network.model.PostResponse
import retrofit2.http.GET

interface AffirmationService {
    @GET("users/1/posts")
    suspend fun fetchPosts(): List<PostResponse>

    @GET("albums/1/photos")
    suspend fun fetchPhotos(): List<PhotoResponse>
}