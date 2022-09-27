package ru.gushchin.feature_detail.di

import android.content.Context
import dagger.Component
import ru.gushchin.core_local_storage.data.api.LocalStorageApi
import ru.gushchin.core_network.data.api.NetworkApi
import ru.gushchin.feature_detail.presentation.DetailFragment

@Component(
    modules = [FeatureDetailModule::class],
    dependencies = [FeatureDetailDependencies::class, Context::class]
)
abstract class FeatureDetailComponent {
    companion object{
        @Volatile
        var featureDetailComponent: FeatureDetailComponent? = null
            private set

        @Volatile
        var applicationContext: Context? = null
            private set

        fun initAndGet(featureDetailDependencies: FeatureDetailDependencies,
            context: Context): FeatureDetailComponent? {
            if (featureDetailComponent == null) {
                synchronized(FeatureDetailComponent::class) {
                    featureDetailComponent = DaggerFeatureDetailComponent.builder()
                        .featureDetailDependencies(featureDetailDependencies)
                        .context(context)
                        .build()
                    applicationContext = context
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