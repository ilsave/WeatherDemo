package ru.gushchin.feature_favorite.presentation

import ru.gushchin.feature_favorite.domain.Weather

sealed class FavoriteListUiState {
    object Loading : FavoriteListUiState()
    class Loaded(val itemState: Weather) : FavoriteListUiState()
    class Error(val message: String) : FavoriteListUiState()
}