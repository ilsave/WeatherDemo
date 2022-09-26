package ru.gushchin.weatherapp.di

import dagger.Component
import ru.gushchin.core_local_storage.di.LocalStorageModule

@Component(modules = [AppModule::class, LocalStorageModule::class])
interface AppComponent