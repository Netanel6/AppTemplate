package com.example.app.feature.sample.di

import com.example.app.feature.sample.data.repository.SampleDetailRepositoryImpl
import com.example.app.feature.sample.domain.repository.SampleDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SampleDetailModule {
    @Binds
    abstract fun bindSampleDetailRepository(
        repository: SampleDetailRepositoryImpl,
    ): SampleDetailRepository
}
