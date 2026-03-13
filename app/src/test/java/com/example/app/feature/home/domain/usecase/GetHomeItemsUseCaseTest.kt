package com.example.app.feature.home.domain.usecase

import com.example.app.core.architecture.result.AppResult
import com.example.app.core.architecture.result.ErrorType
import com.example.app.core.testing.fake.FakeSampleRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetHomeItemsUseCaseTest {
    @Test
    fun `returns repository refresh result`() = runTest {
        val repository = FakeSampleRepository().apply {
            refreshResult = AppResult.Error(ErrorType.NETWORK, "offline")
        }
        val useCase = GetHomeItemsUseCase(repository)

        val result = useCase()

        assertEquals(AppResult.Error(ErrorType.NETWORK, "offline"), result)
    }
}
