package com.example.app.feature.home.domain.usecase

import com.example.app.core.architecture.result.AppResult
import com.example.app.feature.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeItemsUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(): AppResult<Unit> = repository.refresh()
}
