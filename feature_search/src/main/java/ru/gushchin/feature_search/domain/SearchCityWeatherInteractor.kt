package ru.gushchin.feature_search.domain

import ru.gushchin.feature_search.data.SearchWeatherRepositoryImpl
import ru.gushchin.feature_search.presentation.SillySearchCity

class SearchCityWeatherInteractor: SearchCityWeatherUseCase {

    //INJECT repo
    val repository = SearchWeatherRepositoryImpl()

    override suspend fun getSearchCityWeather(name: String): Resource<SillySearchCity> {
        return repository.getCityWeather(name)
    }
}