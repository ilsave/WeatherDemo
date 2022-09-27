package ru.gushchin.feature_search.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import ru.gushchin.feature_search.data.SearchWeatherRepository
import ru.gushchin.feature_search.data.SearchWeatherRepositoryImpl
import ru.gushchin.feature_search.domain.SearchCityWeatherInteractor
import ru.gushchin.feature_search.domain.SearchCityWeatherUseCase
import ru.gushchin.feature_search.presentation.SearchViewModel

@Module
interface FeatureSearchModule {
    @Binds
    fun bindSearchWeatherRepository(repo: SearchWeatherRepositoryImpl): SearchWeatherRepository

    @Binds
    fun bindSearchCityWeatherInteractor(interactor: SearchCityWeatherInteractor): SearchCityWeatherUseCase

    @Binds
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}