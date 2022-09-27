package ru.gushchin.feature_search.presentation

sealed class SearchCityUiState {
    object WaitingForSearching : SearchCityUiState()
    object Loading : SearchCityUiState()
    class Loaded(val itemState: SillySearchCity) : SearchCityUiState()
    class Error(val message: String) : SearchCityUiState()
}