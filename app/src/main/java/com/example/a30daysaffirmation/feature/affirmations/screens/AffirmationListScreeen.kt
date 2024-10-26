package com.example.a30daysaffirmation.feature.affirmations.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.a30daysaffirmation.R
import com.example.a30daysaffirmation.core.model.AffirmationData
import com.example.a30daysaffirmation.core.ui.theme._30DaysAffirmationTheme
import com.example.a30daysaffirmation.feature.affirmations.viewmodel.AffirmationViewModel
import com.example.a30daysaffirmation.feature.affirmations.viewmodel.AffirmationsUiState

@Composable
fun AffirmationListScreen(
    viewModel: AffirmationViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    AffirmationListScreen(uiState.value, modifier)
}

@Composable
fun AffirmationListScreen(state: AffirmationsUiState, modifier: Modifier = Modifier) {
    Surface(modifier.fillMaxSize()) {
        when (state) {
            is AffirmationsUiState.Error -> Text(stringResource(R.string.default_error_message))
            AffirmationsUiState.Loading -> Text(stringResource(R.string.loading_message))
            is AffirmationsUiState.Success -> AffirmationList(state.affirmations)
        }
    }
}

@Composable
fun AffirmationList(affirmations: List<AffirmationData>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        itemsIndexed(affirmations) { index, affirmation ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Day ${index + 1}")
                    Text(affirmation.title)
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        model = "https://raw.githubusercontent.com/android/nowinandroid/refs/heads/main/docs/images/modularization-graph.drawio.png",
                        contentDescription = "Affirmation Image",
                        contentScale = ContentScale.FillBounds
                    )
                    Text(affirmation.body)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AffirmationScreenPreview() {
    _30DaysAffirmationTheme {
        AffirmationListScreen(
            AffirmationsUiState.Success(
                listOf<AffirmationData>(
                    AffirmationData(
                        0,
                        "title",
                        "This is a description",
                        "https://github.com/android/nowinandroid/blob/main/docs/images/modularization-graph.drawio.png"
                    ),
                    AffirmationData(
                        1,
                        "title 1",
                        "This is a description",
                        "https://github.com/android/nowinandroid/blob/main/docs/images/modularization-graph.drawio.png"
                    ),
                    AffirmationData(
                        2,
                        "title 2",
                        "This is a description",
                        "https://github.com/android/nowinandroid/blob/main/docs/images/modularization-graph.drawio.png"
                    ),
                )
            )
        )
    }
}