package ru.gushchin.feature_search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.gushchin.feature_search.domain.Resource
import ru.gushchin.feature_search.domain.SearchCityWeatherUseCase
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val useCase: SearchCityWeatherUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<SearchCityUiState>(SearchCityUiState.WaitingForSearching)
    val uiState: StateFlow<SearchCityUiState> = _uiState

    fun getCityWeather(name: String) {
        _uiState.value = SearchCityUiState.Loading
        viewModelScope.launch {
            useCase.getSearchCityWeather(name).also { result ->
                when (result) {
                    is Resource.Success -> {
                        // avoid calling !!
                        _uiState.value = SearchCityUiState.Loaded(result.data!!)
                    }
                    is Resource.Error -> {
                        _uiState.value = SearchCityUiState.Error(result.message!!)
                    }
                }
            }
        }
    }
}