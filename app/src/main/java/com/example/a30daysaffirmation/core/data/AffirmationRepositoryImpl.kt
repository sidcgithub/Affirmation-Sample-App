package com.example.a30daysaffirmation.core.data

import com.example.a30daysaffirmation.core.model.AffirmationData
import com.example.a30daysaffirmation.core.network.AffirmationService
import com.example.a30daysaffirmation.core.network.model.PhotoResponse
import com.example.a30daysaffirmation.core.network.model.PostResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AffirmationRepositoryImpl @Inject constructor(val service: AffirmationService) :
    AffirmationRepository {
    override fun loadAffirmationsData(): Flow<Resource<List<AffirmationData>>> =
        flow<Resource<List<AffirmationData>>> {
            var deferredPosts: Deferred<List<PostResponse>>
            var deferredPhotoResponse: Deferred<List<PhotoResponse>>
            coroutineScope {
                deferredPosts = async { service.fetchPosts() }
                deferredPhotoResponse = async { service.fetchPhotos() }
            }

            try {
                val results = awaitAll(deferredPosts, deferredPhotoResponse)
                val postResponse = results[0] as List<PostResponse>
                val photoResponse = results[1] as List<PhotoResponse>

                val affirmationData = postResponse.mapIndexed { index, post ->
                    AffirmationData(
                        id = post.id,
                        title = post.title,
                        body = post.body,
                        imageUrl = photoResponse.find { it.id == post.id }?.url
                            ?: ""
                    )
                }

                emit(Resource.Success(data = affirmationData))
            } catch (e: Exception) {
                emit(Resource.Error<List<AffirmationData>>(message = "Error!"))
            }
        }
}
