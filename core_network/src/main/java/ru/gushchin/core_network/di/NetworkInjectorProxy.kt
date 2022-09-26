package ru.gushchin.core_network.di

import androidx.work.WorkManager

object NetworkInjectorProxy {
    fun initWorkManager(workManager: WorkManager) {
        NetworkComponent.initAndGet(workManager)
    }
}