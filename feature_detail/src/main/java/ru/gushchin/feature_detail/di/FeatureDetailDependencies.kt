package ru.gushchin.feature_detail.di

import ru.gushchin.core_local_storage.data.api.LocalDatabaseApi

interface FeatureDetailDependencies {
    fun localDatabaseApi(): LocalDatabaseApi
}