package ru.gushchin.feature_detail.di

import dagger.Component
import ru.gushchin.core_local_storage.data.api.LocalStorageApi
import ru.gushchin.core_network.data.api.NetworkApi
import ru.gushchin.feature_detail.presentation.DetailFragment

@Component(
    modules = [FeatureDetailModule::class],
    dependencies = [FeatureDetailDependencies::class]
)
abstract class FeatureDetailComponent {
    companion object{
        @Volatile
        var featureDetailComponent: FeatureDetailComponent? = null
            private set

        fun initAndGet(featureDetailDependencies: FeatureDetailDependencies): FeatureDetailComponent? {
            if (featureDetailComponent == null) {
                synchronized(FeatureDetailComponent::class) {
                    featureDetailComponent = DaggerFeatureDetailComponent.builder()
                        .featureDetailDependencies(featureDetailDependencies)
                        .build()
                }
            }
            return featureDetailComponent
        }

        fun get(): FeatureDetailComponent? {
            if (featureDetailComponent == null) {
                throw RuntimeException("you must call initAndGet() method first")
            }
            return featureDetailComponent
        }
    }

    abstract fun inject(fragment: DetailFragment)

    @Component(dependencies = [LocalStorageApi::class, NetworkApi::class])
    interface FeatureDetailDependenciesComponent: FeatureDetailDependencies
}