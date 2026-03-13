package com.example.app.feature.home.data.repository

import com.example.app.core.architecture.result.AppResult
import com.example.app.core.architecture.result.ErrorType
import com.example.app.core.common.util.NetworkMonitor
import com.example.app.feature.home.data.local.HomeLocalDataSource
import com.example.app.feature.home.data.mapper.toDomain
import com.example.app.feature.home.data.mapper.toEntity
import com.example.app.feature.home.data.mapper.toRepositoryModel
import com.example.app.feature.home.domain.model.HomeItem
import com.example.app.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val localDataSource: HomeLocalDataSource,
    private val remoteDataSource: com.example.app.feature.home.data.remote.HomeRemoteDataSource,
    private val networkMonitor: NetworkMonitor,
) : HomeRepository {
    override fun observeItems(): Flow<List<HomeItem>> = localDataSource.observeItems().map { items ->
        items.map { it.toRepositoryModel().toDomain() }
    }

    override suspend fun refresh(): AppResult<Unit> {
        if (!networkMonitor.isOnline.first()) {
            return AppResult.Error(
                type = ErrorType.NO_CONNECTION,
                message = "No internet connection available.",
            )
        }

        return when (val result = remoteDataSource.fetchItems()) {
            is AppResult.Success -> {
                localDataSource.replaceAll(result.data.map { it.toEntity() })
                AppResult.Success(Unit)
            }
            is AppResult.Error -> result
        }
    }

    override suspend fun getItem(id: Long): HomeItem? = localDataSource.getItem(id)
        ?.toRepositoryModel()
        ?.toDomain()
}
