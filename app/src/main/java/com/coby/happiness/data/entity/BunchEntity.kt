package com.coby.happiness.data.entity

import android.graphics.Bitmap
import android.provider.ContactsContract.Data
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "bunches")
data class BunchEntity(
    @PrimaryKey val id: UUID,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val title: String,
    val image: Bitmap?,
    val memoryIds: List<UUID>
)
