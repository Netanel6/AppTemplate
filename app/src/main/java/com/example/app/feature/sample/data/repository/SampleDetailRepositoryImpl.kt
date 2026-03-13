package com.example.app.feature.sample.data.repository

import com.example.app.core.architecture.result.AppResult
import com.example.app.feature.sample.data.mapper.missingDetailError
import com.example.app.feature.sample.data.mapper.toDetail
import com.example.app.feature.sample.data.source.SampleDetailDataSource
import com.example.app.feature.sample.domain.model.SampleDetail
import com.example.app.feature.sample.domain.repository.SampleDetailRepository
import javax.inject.Inject

class SampleDetailRepositoryImpl @Inject constructor(
    private val dataSource: SampleDetailDataSource,
) : SampleDetailRepository {
    override suspend fun getDetail(id: Long): AppResult<SampleDetail> {
        val item = dataSource.getItem(id) ?: return missingDetailError(id)
        return AppResult.Success(item.toDetail())
    }
}
