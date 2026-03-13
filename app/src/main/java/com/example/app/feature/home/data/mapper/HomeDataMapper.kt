package com.example.app.feature.home.data.mapper

import com.example.app.core.database.entity.SampleItemEntity
import com.example.app.core.network.model.SampleItemDto
import com.example.app.feature.home.data.model.HomeRepositoryModel
import com.example.app.feature.home.domain.model.HomeItem

internal fun SampleItemDto.toEntity(): SampleItemEntity = SampleItemEntity(
    id = id,
    title = title.replaceFirstChar { it.uppercase() },
    subtitle = "Cached from the placeholder API for starter-template usage.",
    imageUrl = imageUrl,
)

internal fun SampleItemEntity.toRepositoryModel(): HomeRepositoryModel = HomeRepositoryModel(
    id = id,
    title = title,
    subtitle = subtitle,
    imageUrl = imageUrl,
)

internal fun HomeRepositoryModel.toDomain(): HomeItem = HomeItem(
    id = id,
    title = title,
    subtitle = subtitle,
    imageUrl = imageUrl,
)
