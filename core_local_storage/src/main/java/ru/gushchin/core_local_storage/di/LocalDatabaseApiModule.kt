package ru.gushchin.core_local_storage.di

import dagger.Binds
import dagger.Module
import ru.gushchin.core_local_storage.data.api.LocalDatabaseApi
import ru.gushchin.core_local_storage.data.impl.LocalDatabaseApiImpl

@Module
interface LocalDatabaseApiModule {
    @Binds
    fun bindLocalDatabase(api: LocalDatabaseApiImpl): LocalDatabaseApi
}