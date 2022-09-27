package ru.gushchin.feature_favorite.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.gushchin.feature_favorite.domain.FavoriteCityListInteractor
import ru.gushchin.feature_favorite.domain.FavoriteCityListUseCase
import ru.gushchin.feature_favorite.domain.Resource
import javax.inject.Inject

class FavoriteListViewModel @Inject constructor(
    private val useCase: FavoriteCityListUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<FavoriteListUiState>(FavoriteListUiState.Loading)
    val uiState: StateFlow<FavoriteListUiState> = _uiState


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