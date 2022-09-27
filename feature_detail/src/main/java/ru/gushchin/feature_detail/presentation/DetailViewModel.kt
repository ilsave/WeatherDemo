package ru.gushchin.feature_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.gushchin.feature_detail.data.DefaultLocationTracker
import ru.gushchin.feature_detail.data.LocationTracker
import ru.gushchin.feature_detail.data.models.City
import ru.gushchin.feature_detail.domain.CurrentCityWeatherUseCases
import ru.gushchin.feature_detail.domain.Resource
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val useCase: CurrentCityWeatherUseCases,
    private val locationTracker: LocationTracker
): ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState

    suspend fun getTheCurrent() {
        _uiState.value = DetailUiState.Loading
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let { location ->
                useCase.getCurrentCityWeatherBy(
                    location.latitude,
                    location.longitude
                ).also { result ->
                    when (result) {
                        is Resource.Success -> {
                            _uiState.value = DetailUiState.Loaded(result.data!!)
                        }
                        is Resource.Error -> {
                            _uiState.value = DetailUiState.Error(result.message!!)
                        }
                    }
                }
            }
        }
    }
}