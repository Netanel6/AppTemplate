package com.example.app.core.network.mapper

import com.example.app.core.architecture.result.AppResult
import com.example.app.core.architecture.result.ErrorType
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class NetworkErrorMapper @Inject constructor() {
    fun map(throwable: Throwable): AppResult.Error = when (throwable) {
        is SocketTimeoutException -> AppResult.Error(ErrorType.TIMEOUT, throwable.message, throwable)
        is IOException -> AppResult.Error(ErrorType.NETWORK, throwable.message, throwable)
        is HttpException -> AppResult.Error(ErrorType.SERVER, throwable.message(), throwable)
        is SerializationException -> AppResult.Error(ErrorType.SERIALIZATION, throwable.message, throwable)
        else -> AppResult.Error(ErrorType.UNKNOWN, throwable.message, throwable)
    }
}
