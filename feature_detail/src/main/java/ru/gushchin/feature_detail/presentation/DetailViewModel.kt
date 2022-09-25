package ru.gushchin.feature_detail.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.gushchin.feature_detail.domain.CurrentCityWeatherInteractor

class DetailViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState

    val useCase = CurrentCityWeatherInteractor()

    suspend fun getTheCurrent() {
        viewModelScope.launch {
            useCase.getCurrentCityWeatherBy(5)
        }
    }

    data class DetailCityWeather(
        val name: String,
        val weather: String
    )

    sealed class DetailUiState {
        object Loading : DetailUiState()
        class Loaded(val itemState: DetailCityWeather) : DetailUiState()
        class Error(@StringRes val message: Int) : DetailUiState()
    }
}