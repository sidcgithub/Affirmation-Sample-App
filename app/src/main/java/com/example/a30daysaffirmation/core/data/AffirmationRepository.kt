package com.example.a30daysaffirmation.core.data

import com.example.a30daysaffirmation.core.model.AffirmationData
import kotlinx.coroutines.flow.Flow

interface AffirmationRepository {
    fun loadAffirmationsData(): Flow<Resource<List<AffirmationData>>>
}