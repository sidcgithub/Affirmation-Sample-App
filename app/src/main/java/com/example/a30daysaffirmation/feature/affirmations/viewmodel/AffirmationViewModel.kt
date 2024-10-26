package com.example.a30daysaffirmation.feature.affirmations.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a30daysaffirmation.core.data.AffirmationRepository
import com.example.a30daysaffirmation.core.data.Resource
import com.example.a30daysaffirmation.core.model.AffirmationData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AffirmationViewModel @Inject constructor(repository: AffirmationRepository): ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<AffirmationsUiState> = repository.loadAffirmationsData().flatMapLatest { affirmationsResource ->
        flowOf(AffirmationsUiState.Loading)
        flow {
            emit(when (affirmationsResource) {
                is Resource.Error -> AffirmationsUiState.Error(message = affirmationsResource.message)
                is Resource.Success -> AffirmationsUiState.Success(affirmations = affirmationsResource.data)
            })
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = AffirmationsUiState.Loading
    )
}

sealed interface AffirmationsUiState {
    data class Success(val affirmations: List<AffirmationData>) : AffirmationsUiState
    data class Error(val message: String) : AffirmationsUiState
    data object Loading : AffirmationsUiState
}