package com.example.app.feature.sample.domain.usecase

import com.example.app.core.architecture.result.AppResult
import com.example.app.feature.sample.domain.model.SampleDetail
import com.example.app.feature.sample.domain.repository.SampleDetailRepository
import javax.inject.Inject

class GetSampleDetailUseCase @Inject constructor(
    private val repository: SampleDetailRepository,
) {
    suspend operator fun invoke(id: Long): AppResult<SampleDetail> = repository.getDetail(id)
}
