package com.example.app.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    val message: String? = null,
)
