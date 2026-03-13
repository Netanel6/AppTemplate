package com.example.app.feature.home.domain.usecase

import com.example.app.feature.home.domain.model.HomeItem
import com.example.app.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveHomeItemsUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    operator fun invoke(): Flow<List<HomeItem>> = repository.observeItems()
}
