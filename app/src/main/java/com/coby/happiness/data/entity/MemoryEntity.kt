package com.coby.happiness.data.entity

import android.provider.ContactsContract.Data
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coby.happiness.domain.model.LocationModel
import com.coby.happiness.domain.type.MemoryType
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "memories")
data class MemoryEntity(
    @PrimaryKey val id: UUID,
    val date: LocalDateTime,
    val type: MemoryType,
    val title: String,
    val note: String,
    val location: LocationModel?,
    val photosData: List<ByteArray>
)