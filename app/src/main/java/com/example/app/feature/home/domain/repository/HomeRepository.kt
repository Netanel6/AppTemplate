package com.example.app.feature.home.domain.repository

import com.example.app.core.architecture.result.AppResult
import com.example.app.feature.home.domain.model.HomeItem
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun observeItems(): Flow<List<HomeItem>>
    suspend fun refresh(): AppResult<Unit>
    suspend fun getItem(id: Long): HomeItem?
}
