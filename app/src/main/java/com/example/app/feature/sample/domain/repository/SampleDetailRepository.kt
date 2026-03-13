package com.example.app.feature.sample.domain.repository

import com.example.app.core.architecture.result.AppResult
import com.example.app.feature.sample.domain.model.SampleDetail

interface SampleDetailRepository {
    suspend fun getDetail(id: Long): AppResult<SampleDetail>
}
