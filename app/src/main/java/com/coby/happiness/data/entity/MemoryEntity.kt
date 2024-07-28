package com.coby.happiness.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "memories")
data class MemoryEntity(
    @PrimaryKey val id: UUID,
    val date: Long,
    val type: String,
    val title: String,
    val note: String,
    val location: String,
    val photosData: List<String>
)