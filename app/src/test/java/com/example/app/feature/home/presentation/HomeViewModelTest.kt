package com.example.app.feature.home.presentation

import app.cash.turbine.test
import com.example.app.core.architecture.result.AppResult
import com.example.app.core.architecture.result.ErrorType
import com.example.app.core.testing.fake.FakeSampleRepository
import com.example.app.core.testing.rules.MainDispatcherRule
import com.example.app.feature.home.domain.model.HomeItem
import com.example.app.feature.home.domain.usecase.GetHomeItemsUseCase
import com.example.app.feature.home.domain.usecase.ObserveHomeItemsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `emits success state when repository has items`() = runTest {
        val repository = FakeSampleRepository(
            initialItems = listOf(
                HomeItem(1L, "Title", "Subtitle", "https://picsum.photos/200"),
            ),
        )
        val viewModel = HomeViewModel(
            getHomeItemsUseCase = GetHomeItemsUseCase(repository),
            observeHomeItemsUseCase = ObserveHomeItemsUseCase(repository),
        )

        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(false, state.isLoading)
        assertEquals(1, state.items.size)
    }

    @Test
    fun `emits error message when refresh fails`() = runTest {
        val repository = FakeSampleRepository().apply {
            refreshResult = AppResult.Error(ErrorType.NETWORK, "Request failed")
        }
        val viewModel = HomeViewModel(
            getHomeItemsUseCase = GetHomeItemsUseCase(repository),
            observeHomeItemsUseCase = ObserveHomeItemsUseCase(repository),
        )

        viewModel.events.test {
            advanceUntilIdle()
            assertEquals(
                HomeContract.Event.ShowMessage("Request failed"),
                awaitItem(),
            )
        }
    }
}
