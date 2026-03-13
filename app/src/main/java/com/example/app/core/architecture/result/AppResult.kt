package com.example.app.core.architecture.result

sealed interface AppResult<out T> {
    data class Success<T>(val data: T) : AppResult<T>
    data class Error(
        val type: ErrorType,
        val message: String? = null,
        val cause: Throwable? = null,
    ) : AppResult<Nothing>
}
