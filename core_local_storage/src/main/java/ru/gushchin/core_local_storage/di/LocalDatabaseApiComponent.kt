package ru.gushchin.core_local_storage.di

import dagger.Component
import ru.gushchin.core_local_storage.data.api.LocalStorageApi
import javax.inject.Singleton

@Singleton
@Component(modules = [LocalDatabaseApiModule::class])
interface LocalDatabaseApiComponent: LocalStorageApi