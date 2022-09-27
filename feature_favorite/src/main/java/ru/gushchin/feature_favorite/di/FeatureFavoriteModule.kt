package ru.gushchin.feature_favorite.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import ru.gushchin.feature_favorite.data.repository.FavoriteWeatherRepository
import ru.gushchin.feature_favorite.data.repository.FavoriteWeatherRepositoryImpl
import ru.gushchin.feature_favorite.domain.FavoriteCityListInteractor
import ru.gushchin.feature_favorite.domain.FavoriteCityListUseCase
import ru.gushchin.feature_favorite.presentation.FavoriteListViewModel

@Module
interface FeatureFavoriteModule {
    @Binds
    fun bindsFavoriteWeatherRepository(repo: FavoriteWeatherRepositoryImpl): FavoriteWeatherRepository

    @Binds
    fun bindsFavoriteCityInteractor(interactor: FavoriteCityListInteractor): FavoriteCityListUseCase

    @Binds
    fun bindsFavoriteListViewModel(viewModel: FavoriteListViewModel): ViewModel
}