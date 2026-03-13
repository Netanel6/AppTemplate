package com.example.app.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SampleItemDto(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("url") val imageUrl: String,
    @SerialName("thumbnailUrl") val thumbnailUrl: String,
)
