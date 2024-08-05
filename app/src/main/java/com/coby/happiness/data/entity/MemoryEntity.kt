package com.coby.happiness.data.entity

import android.graphics.Bitmap
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coby.happiness.domain.model.LocationModel
import com.coby.happiness.domain.type.MemoryType
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "memories")
data class MemoryEntity(
    @PrimaryKey val id: UUID,
    @Embedded val date: LocalDateTime,
    val type: MemoryType,
    val title: String,
    val note: String,
    @Embedded val location: LocationModel?,
    val photos: List<Bitmap>,
) {
    constructor() : this(UUID.randomUUID(), LocalDateTime.now(), MemoryType.TRIP, "", "", null, emptyList())
}