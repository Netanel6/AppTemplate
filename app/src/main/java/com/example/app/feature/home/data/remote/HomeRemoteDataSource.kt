package com.example.app.feature.home.data.remote

import com.example.app.core.architecture.result.AppResult
import com.example.app.core.network.adapter.ApiCallHelper
import com.example.app.core.network.api.SampleApiService
import com.example.app.core.network.model.SampleItemDto
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val apiService: SampleApiService,
    private val apiCallHelper: ApiCallHelper,
) {
    suspend fun fetchItems(): AppResult<List<SampleItemDto>> = apiCallHelper.safeCall {
        apiService.getSampleItems()
    }
}
