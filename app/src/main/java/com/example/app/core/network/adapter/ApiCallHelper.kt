package com.example.app.core.network.adapter

import com.example.app.core.architecture.result.AppResult
import com.example.app.core.network.mapper.NetworkErrorMapper
import javax.inject.Inject

class ApiCallHelper @Inject constructor(
    private val errorMapper: NetworkErrorMapper,
) {
    suspend fun <T> safeCall(block: suspend () -> T): AppResult<T> = try {
        AppResult.Success(block())
    } catch (throwable: Throwable) {
        errorMapper.map(throwable)
    }
}
