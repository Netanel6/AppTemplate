package com.example.app.core.database.di

import android.content.Context
import androidx.room.Room
import com.example.app.core.database.AppDatabase
import com.example.app.core.database.dao.SampleItemDao
import com.example.app.core.database.model.DatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DatabaseConstants.DATABASE_NAME,
    ).fallbackToDestructiveMigration(dropAllTables = true).build()

    @Provides
    fun provideSampleItemDao(database: AppDatabase): SampleItemDao = database.sampleItemDao()
}
