package com.example.app.core.testing.fake

import com.example.app.core.architecture.result.AppResult
import com.example.app.feature.home.domain.model.HomeItem
import com.example.app.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeSampleRepository(
    initialItems: List<HomeItem> = emptyList(),
) : HomeRepository {
    private val items = MutableStateFlow(initialItems)
    var refreshResult: AppResult<Unit> = AppResult.Success(Unit)

    override fun observeItems(): Flow<List<HomeItem>> = items

    override suspend fun refresh(): AppResult<Unit> = refreshResult

    override suspend fun getItem(id: Long): HomeItem? = items.value.firstOrNull { it.id == id }

    fun updateItems(newItems: List<HomeItem>) {
        items.value = newItems
    }
}
