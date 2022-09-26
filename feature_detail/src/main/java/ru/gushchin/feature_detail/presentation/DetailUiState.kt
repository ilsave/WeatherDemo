package ru.gushchin.feature_detail.presentation

import ru.gushchin.core_network.data.models.WeatherDTO

sealed class DetailUiState {
    object Loading : DetailUiState()
    class Loaded(val itemState: WeatherDTO) : DetailUiState()
    class Error(val message: String) : DetailUiState()
}