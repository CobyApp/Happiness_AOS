package com.coby.happiness.data.mapper

import com.coby.happiness.data.entity.MemoryEntity
import com.coby.happiness.domain.model.MemoryModel

// MemoryMapper.kt
fun MemoryEntity.toMemoryModel(): MemoryModel {
    return MemoryModel(
        id = this.id,
        date = this.date,
        type = this.type,
        title = this.title,
        note = this.note,
        location = this.location,
//        photos = this.photos,
        isFirst = false
    )
}

fun MemoryModel.toMemoryEntity(): MemoryEntity {
    return MemoryEntity(
        id = this.id,
        date = this.date,
        type = this.type,
        title = this.title,
        note = this.note,
        location = this.location,
        photos = this.photos.toString()
    )
}
