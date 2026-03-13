package com.example.app.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sample_items")
data class SampleItemEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val subtitle: String,
    val imageUrl: String,
)
