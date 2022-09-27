package ru.gushchin.feature_favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.gushchin.feature_favorite.domain.FavoriteCityListInteractor
import ru.gushchin.feature_favorite.domain.Resource

class FavoriteListViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<FavoriteListUiState>(FavoriteListUiState.Loading)
    val uiState: StateFlow<FavoriteListUiState> = _uiState

    //INJECT USECASE
    val useCase = FavoriteCityListInteractor()

    fun getFavoriteCityList() {
        _uiState.value = FavoriteListUiState.Loading
        viewModelScope.launch {
            useCase.getFavoriteCityList().also { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.value = FavoriteListUiState.Loaded(result.data!!)
                    }
                    is Resource.Error -> {
                        _uiState.value = FavoriteListUiState.Error(result.message!!)
                    }
                }
            }
        }
    }


}