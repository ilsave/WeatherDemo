package ru.gushchin.feature_detail.presentation

import ru.gushchin.feature_detail.data.models.Weather


sealed class DetailUiState {
    object Loading : DetailUiState()
    class Loaded(val itemState: Weather) : DetailUiState()
    class Error(val message: String) : DetailUiState()
}