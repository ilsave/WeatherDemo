package ru.gushchin.feature_detail.presentation

import ru.gushchin.core_network.model.WeatherDTO

sealed class DetailUiState {
    object Loading : DetailUiState()
    class Loaded(val itemState: WeatherDTO? = null) : DetailUiState()
    class Error(val message: String? = null) : DetailUiState()
}