package com.example.app.feature.home.presentation.mapper

import com.example.app.feature.home.domain.model.HomeItem
import com.example.app.feature.home.presentation.model.HomeItemUiModel

internal fun HomeItem.toUiModel(): HomeItemUiModel = HomeItemUiModel(
    id = id,
    title = title,
    subtitle = subtitle,
    imageUrl = imageUrl,
)
