package com.coby.happiness.data.entity

import android.graphics.Bitmap
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "bunches")
data class BunchEntity(
    @PrimaryKey val id: UUID,
    @Embedded val startDate: LocalDateTime,
    @Embedded val endDate: LocalDateTime,
    val title: String,
    val image: Bitmap?,
    val memoryIds: String // 변환기로 처리
) {
    constructor() : this(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), "", null, "")
}
