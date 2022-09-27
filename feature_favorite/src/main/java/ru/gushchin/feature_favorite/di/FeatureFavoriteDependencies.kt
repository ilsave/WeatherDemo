package ru.gushchin.feature_favorite.di

import ru.gushchin.core_local_storage.data.api.LocalDatabaseApi

interface FeatureFavoriteDependencies {
    fun localDatabaseApi(): LocalDatabaseApi
}