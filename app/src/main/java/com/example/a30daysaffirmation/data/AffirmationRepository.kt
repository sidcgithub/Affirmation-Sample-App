package com.example.a30daysaffirmation.data

import com.example.a30daysaffirmation.model.AffirmationData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AffirmationRepository {
    fun loadAffirmationsData(): Flow<Resource<Any?>>
}