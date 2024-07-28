package com.coby.happiness.domain.type

import android.content.Context
import android.graphics.drawable.Drawable

enum class MemoryType(val title: String, val description: String) {
    TRIP("여행", "여행의 즐거움을 담은 사진을 골라주세요."),
    FOOD("음식", "맛있는 음식을 즐겼던 사진을 골라주세요."),
    HOBBY("취미", "내가 좋아하는 것들의 사진을 골라주세요."),
    MOMENT("순간", "기억에 남는 특별한 순간의 사진을 골라주세요.");

    companion object {
        fun fromRawValue(value: String): MemoryType? {
            return values().find { it.name.equals(value, ignoreCase = true) }
        }
    }

    fun getIcon(context: Context): Drawable? {
        val resourceName = when (this) {
            TRIP -> "ic_trip"
            FOOD -> "ic_food"
            HOBBY -> "ic_favorite"
            MOMENT -> "ic_flag"
        }
        val resourceId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)
        return context.getDrawable(resourceId)
    }
}
