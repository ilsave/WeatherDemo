package ru.gushchin.feature_favorite.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel

class FavoriteListViewModel: ViewModel() {

    data class FavoriteUiState(
        val cities: List<String>
    )

    sealed class FavoriteListUiState {
        object Loading : FavoriteListUiState()
        class Loaded(val itemState: FavoriteUiState) : FavoriteListUiState()
        class Error(@StringRes val message: Int) : FavoriteListUiState()
    }
}