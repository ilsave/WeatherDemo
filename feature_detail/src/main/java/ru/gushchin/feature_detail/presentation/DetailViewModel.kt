package ru.gushchin.feature_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.gushchin.feature_detail.data.DefaultLocationTracker
import ru.gushchin.feature_detail.data.models.City
import ru.gushchin.feature_detail.domain.CurrentCityWeatherUseCases
import ru.gushchin.feature_detail.domain.Resource
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val useCase: CurrentCityWeatherUseCases
): ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState

    private val locationTracker = DefaultLocationTracker()

    suspend fun getTheCurrent() {
        _uiState.value = DetailUiState.Loading
        viewModelScope.launch {
           // locationTracker.getCurrentLocation()?.let { location ->
                useCase.getCurrentCityWeatherBy(City(
                    "London",
                    36.4761,
                    -119.4432,
                    false)).also { result ->
                    when (result) {
                        is Resource.Success -> {
                            _uiState.value = DetailUiState.Loaded(result.data!!)
                        }
                        is Resource.Error -> {
                            _uiState.value = DetailUiState.Error(result.message!!)
                        }
                    }
                }
            //}
        }
    }
}