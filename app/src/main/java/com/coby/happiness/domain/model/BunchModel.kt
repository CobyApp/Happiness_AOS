package com.coby.happiness.domain.model

import android.graphics.Bitmap
import java.time.LocalDateTime
import java.util.UUID

data class BunchModel(
    val id: UUID = UUID.randomUUID(),
    val startDate: LocalDateTime = LocalDateTime.now(),
    val endDate: LocalDateTime = LocalDateTime.now(),
    val title: String = "",
    val image: Bitmap? = null,
    val imageData: ByteArray? = null,
    val memories: List<MemoryModel> = listOf(),
    val isFirst: Boolean = true
) {

    val term: String
        get() {
            val startDateStr = startDate.formatMid()
            val endDateStr = endDate.formatMid()
            return if (startDateStr == endDateStr) startDateStr else "$startDateStr ~ $endDateStr"
        }

    val termLong: String
        get() {
            val startDateStr = startDate.formatLong()
            val endDateStr = endDate.formatLong()
            return if (startDateStr == endDateStr) startDateStr else "$startDateStr ~ $endDateStr"
        }

    val termShort: String
        get() {
            val startDateStr = startDate.formatShort()
            val endDateStr = endDate.formatShort()
            return if (startDateStr == endDateStr) startDateStr else "$startDateStr ~ $endDateStr"
        }

    val photos: List<Bitmap>
        get() = memories.flatMap { it.photos }

    val isFirstPageDisabled: Boolean
        get() = memories.isEmpty()

    val isSecondPageDisabled: Boolean
        get() = title.isEmpty()

    val isDisabled: Boolean
        get() = isFirstPageDisabled || isSecondPageDisabled
}

fun LocalDateTime.formatMid(): String {
    // 날짜 형식을 "중간" 형식으로 변환하는 로직을 구현합니다.
    return this.toString()
}

fun LocalDateTime.formatLong(): String {
    // 날짜 형식을 "긴" 형식으로 변환하는 로직을 구현합니다.
    return this.toString()
}

fun LocalDateTime.formatShort(): String {
    // 날짜 형식을 "짧은" 형식으로 변환하는 로직을 구현합니다.
    return this.toString()
}