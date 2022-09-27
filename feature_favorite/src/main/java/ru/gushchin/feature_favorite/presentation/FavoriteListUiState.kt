package ru.gushchin.feature_favorite.presentation

import ru.gushchin.feature_favorite.domain.Weather

sealed class FavoriteListUiState {
    object Loading : FavoriteListUiState()
    class Loaded(val itemState: List<SillyWeather?>) : FavoriteListUiState()
    class Error(val message: String) : FavoriteListUiState()
}