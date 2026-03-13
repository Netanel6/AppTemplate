package com.example.app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.app.core.database.entity.SampleItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SampleItemDao {
    @Query("SELECT * FROM sample_items ORDER BY id ASC")
    fun observeAll(): Flow<List<SampleItemEntity>>

    @Query("SELECT * FROM sample_items WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): SampleItemEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<SampleItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: SampleItemEntity)

    @Query("DELETE FROM sample_items")
    suspend fun clear()

    @Transaction
    suspend fun replaceAll(items: List<SampleItemEntity>) {
        clear()
        insertAll(items)
    }
}
