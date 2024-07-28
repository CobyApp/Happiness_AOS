package com.coby.happiness.domain.model

import android.graphics.Bitmap
import com.coby.happiness.domain.type.MemoryType
import java.time.LocalDateTime
import java.util.UUID

data class MemoryModel(
    val id: UUID = UUID.randomUUID(),
    val date: LocalDateTime = LocalDateTime.now(),
    val type: MemoryType = MemoryType.TRIP,
    val title: String = "",
    val note: String = "",
    val location: LocationModel? = null,
    val photos: List<Bitmap> = listOf(),
    val bunches: List<BunchModel> = listOf(),
    val isFirst: Boolean = true
) {

    val isFirstPageDisabled: Boolean
        get() = photos.isEmpty()

    val isSecondPageDisabled: Boolean
        get() = title.isEmpty() || note.isEmpty()

    val isDisabled: Boolean
        get() = isFirstPageDisabled || isSecondPageDisabled
}

fun List<MemoryModel>.getFilteredMemories(type: MemoryType?): List<MemoryModel> {
    return if (type != null) {
        this.filter { it.type == type }
    } else {
        this
    }
}