package ru.gushchin.feature_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.gushchin.feature_detail.domain.CurrentCityWeatherInteractor
import ru.gushchin.feature_detail.domain.Resource

class DetailViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState

    val useCase = CurrentCityWeatherInteractor()

    suspend fun getTheCurrent() {
        _uiState.value = DetailUiState.Loading
        viewModelScope.launch {
            useCase.getCurrentCityWeatherBy().also { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.value = DetailUiState.Loaded(result.data)
                    }
                    is Resource.Error -> {
                        _uiState.value = DetailUiState.Error(result.message)
                    }
                }
            }
        }
    }
}