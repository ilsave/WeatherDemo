package ru.gushchin.feature_search.di

import dagger.Component
import ru.gushchin.core_network.data.api.NetworkApi
import ru.gushchin.feature_search.presentation.SearchFragment

@Component(
    modules = [FeatureSearchModule::class],
    dependencies = [FeatureSearchDependencies::class]
)
abstract class FeatureSearchComponent {
    companion object {
        @Volatile
        var featureSearchComponent: FeatureSearchComponent? = null
            private set

        fun initAndGet(
            featureSearchDependencies: FeatureSearchDependencies
        ): FeatureSearchComponent? {
            if (featureSearchComponent == null) {
                synchronized(FeatureSearchComponent::class) {
                    featureSearchComponent = DaggerFeatureSearchComponent.builder()
                        .featureSearchDependencies(featureSearchDependencies)
                        .build()
                }
            }
            return featureSearchComponent
        }

        fun get(): FeatureSearchComponent? {
            if (featureSearchComponent == null) {
                throw RuntimeException("you must call initAndGet() method first")
            }
            return featureSearchComponent
        }
    }

    abstract fun inject(fragment: SearchFragment)

    @Component(dependencies = [NetworkApi::class])
    interface FeatureSearchDependenciesComponent : FeatureSearchDependencies

}