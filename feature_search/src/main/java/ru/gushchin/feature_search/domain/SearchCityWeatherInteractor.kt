package ru.gushchin.feature_search.domain

import ru.gushchin.feature_search.data.SearchWeatherRepository
import ru.gushchin.feature_search.presentation.SillySearchCity
import javax.inject.Inject

class SearchCityWeatherInteractor @Inject constructor(
    private val repository: SearchWeatherRepository
): SearchCityWeatherUseCase {

    override suspend fun getSearchCityWeather(name: String): Resource<SillySearchCity> {
        return repository.getCityWeather(name)
    }
}