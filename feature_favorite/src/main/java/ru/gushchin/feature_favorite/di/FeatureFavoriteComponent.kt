package ru.gushchin.feature_favorite.di

import dagger.Component
import ru.gushchin.core_local_storage.data.api.LocalStorageApi
import ru.gushchin.feature_favorite.presentation.FavoriteListFragment

@Component(
    modules = [FeatureFavoriteModule::class],
    dependencies = [FeatureFavoriteDependencies::class]
)
abstract class FeatureFavoriteComponent {
    companion object {
        @Volatile
        var featureFavoriteComponent: FeatureFavoriteComponent? = null
            private set

        fun initAndGet(
            featureFavoriteDependencies: FeatureFavoriteDependencies
        ): FeatureFavoriteComponent? {
            if (featureFavoriteComponent == null) {
                synchronized(FeatureFavoriteComponent::class) {
                    featureFavoriteComponent = DaggerFeatureFavoriteComponent.builder()
                        .featureFavoriteDependencies(featureFavoriteDependencies)
                        .build()
                }
            }
            return featureFavoriteComponent
        }

        fun get(): FeatureFavoriteComponent? {
            if (featureFavoriteComponent == null) {
                throw RuntimeException("you must call initAndGet() method first")
            }
            return featureFavoriteComponent
        }
    }

    abstract fun inject(fragment: FavoriteListFragment)

    @Component(dependencies = [LocalStorageApi::class])
    interface FeatureFavoriteDependenciesComponent : FeatureFavoriteDependencies
}