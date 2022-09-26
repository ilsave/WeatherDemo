package ru.gushchin.core_local_storage.di

import android.content.Context

object LocalStorageInjectorProxy {
    fun initContext(context: Context) {
        LocalStorageComponent.initAndGet(context)
    }
}