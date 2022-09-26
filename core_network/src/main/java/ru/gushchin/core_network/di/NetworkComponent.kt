package ru.gushchin.core_network.di

import androidx.work.WorkManager
import dagger.Component

@Component(dependencies = [WorkManager::class])
abstract class NetworkComponent {
    companion object {
        @Volatile
        var instance: NetworkComponent? = null

        @Volatile
        var workManager: WorkManager? = null

        fun initAndGet(workManager: WorkManager): NetworkComponent? {
            if (instance == null) {
                synchronized(NetworkComponent::class) {
                    NetworkComponent.workManager = workManager
                    instance = DaggerNetworkComponent.builder()
                        .workManager(workManager)
                        .build()
                }
            }
            return instance
        }

        fun get(): NetworkComponent {
            return dagger.internal.Preconditions.checkNotNull(instance,
                "NetworkComponent is not initialized yet. Call init first.")!!
        }
    }
}