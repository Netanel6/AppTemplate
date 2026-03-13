package com.example.app.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app.core.database.dao.SampleItemDao
import com.example.app.core.database.entity.SampleItemEntity

@Database(
    entities = [SampleItemEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sampleItemDao(): SampleItemDao
}
