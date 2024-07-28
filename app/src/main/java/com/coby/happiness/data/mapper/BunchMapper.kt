package com.coby.happiness.data.mapper

import com.coby.happiness.data.entity.BunchEntity
import com.coby.happiness.domain.model.BunchModel
import com.coby.happiness.domain.model.MemoryModel

fun BunchEntity.toBunchModel(memories: List<MemoryModel>): BunchModel {
    return BunchModel(
        id = this.id,
        startDate = this.startDate,
        endDate = this.endDate,
        title = this.title,
        image = this.image,
        memories = memories.sortedBy { it.date },
        isFirst = false
    )
}

fun BunchModel.toBunchEntity(): BunchEntity {
    return BunchEntity(
        id = this.id,
        startDate = this.startDate,
        endDate = this.endDate,
        title = this.title,
        image = this.image,
        memoryIds = this.memories.map { it.id }
    )
}
