package ru.gushchin.core_local_storage.data.api

interface LocalStorageApi {
    fun getLocalDatabaseApi(): LocalDatabaseApi
}