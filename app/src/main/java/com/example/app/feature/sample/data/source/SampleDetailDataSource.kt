package com.example.app.feature.sample.data.source

import com.example.app.feature.home.domain.model.HomeItem
import com.example.app.feature.home.domain.repository.HomeRepository
import javax.inject.Inject

class SampleDetailDataSource @Inject constructor(
    private val homeRepository: HomeRepository,
) {
    suspend fun getItem(id: Long): HomeItem? = homeRepository.getItem(id)
}
