package com.example.app.feature.home.data.local

import com.example.app.core.database.dao.SampleItemDao
import com.example.app.core.database.entity.SampleItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(
    private val sampleItemDao: SampleItemDao,
) {
    fun observeItems(): Flow<List<SampleItemEntity>> = sampleItemDao.observeAll()

    suspend fun replaceAll(items: List<SampleItemEntity>) {
        sampleItemDao.replaceAll(items)
    }

    suspend fun getItem(id: Long): SampleItemEntity? = sampleItemDao.getById(id)
}
