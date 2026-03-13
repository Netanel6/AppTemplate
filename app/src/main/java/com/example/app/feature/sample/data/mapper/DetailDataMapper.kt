package com.example.app.feature.sample.data.mapper

import com.example.app.core.architecture.result.AppResult
import com.example.app.core.architecture.result.ErrorType
import com.example.app.feature.home.domain.model.HomeItem
import com.example.app.feature.sample.domain.model.SampleDetail

internal fun HomeItem.toDetail(): SampleDetail = SampleDetail(
    id = id,
    title = title,
    description = subtitle,
    imageUrl = imageUrl,
)

internal fun missingDetailError(id: Long): AppResult.Error = AppResult.Error(
    type = ErrorType.LOCAL,
    message = "Unable to find item #$id in the local cache.",
)
