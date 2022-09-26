package ru.gushchin.feature_detail.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import ru.gushchin.feature_detail.data.WeatherRepository
import ru.gushchin.feature_detail.data.WeatherRepositoryImpl
import ru.gushchin.feature_detail.domain.CurrentCityWeatherInteractor
import ru.gushchin.feature_detail.domain.CurrentCityWeatherUseCases
import ru.gushchin.feature_detail.presentation.DetailViewModel

@Module
interface FeatureDetailModule {
    @Binds
    fun bindCurrentCityWeatherInteractor(interactor: CurrentCityWeatherInteractor): CurrentCityWeatherUseCases

    @Binds
    fun bindWeatherRepository(repositoryImpl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel
}