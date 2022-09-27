package ru.gushchin.feature_detail.di

import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.gushchin.feature_detail.data.DefaultLocationTracker
import ru.gushchin.feature_detail.data.LocationTracker
import ru.gushchin.feature_detail.data.repository.WeatherRepository
import ru.gushchin.feature_detail.data.repository.WeatherRepositoryImpl
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Binds
    fun bindLocationTracker(locationTracker: DefaultLocationTracker): LocationTracker
}