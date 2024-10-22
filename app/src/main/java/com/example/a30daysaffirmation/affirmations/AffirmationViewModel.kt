package com.example.a30daysaffirmation.affirmations

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a30daysaffirmation.data.AffirmationRepository
import com.example.a30daysaffirmation.data.Resource
import com.example.a30daysaffirmation.model.AffirmationData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AffirmationViewModel @Inject constructor(repository: AffirmationRepository): ViewModel() {

    init {
        viewModelScope.launch {

            val result = repository.loadAffirmationsData().last()
            Log.d("AffViewModel", ": ${result.toString()}")
        }
    }
}