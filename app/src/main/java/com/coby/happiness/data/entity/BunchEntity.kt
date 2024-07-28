package com.coby.happiness.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "bunches")
data class BunchEntity(
    @PrimaryKey val id: UUID,
    val startDate: Long,
    val endDate: Long,
    val title: String,
    val imageData: String,
    val memories: List<UUID>
)
