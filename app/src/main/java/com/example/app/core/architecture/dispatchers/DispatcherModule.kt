package com.example.app.core.architecture.dispatchers

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {
    @Binds
    abstract fun bindDispatcherProvider(
        dispatcherProvider: DefaultDispatcherProvider,
    ): DispatcherProvider
}
