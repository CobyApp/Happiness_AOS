package com.coby.happiness.data.service

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.coby.happiness.domain.model.LocationModel
import com.coby.happiness.domain.type.MemoryType
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import java.util.Base64
import java.util.UUID

class RoomConverters {

    @TypeConverter
    fun fromUUID(uuid: UUID): String = uuid.toString()

    @TypeConverter
    fun toUUID(uuid: String): UUID = UUID.fromString(uuid)

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return Base64.getEncoder().encodeToString(outputStream.toByteArray())
    }

    @TypeConverter
    fun toBitmap(data: String): Bitmap {
        val bytes = Base64.getDecoder().decode(data)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    @TypeConverter
    fun fromBitmapList(bitmapList: List<Bitmap>): String {
        return bitmapList.joinToString(",") { fromBitmap(it) }
    }

    @TypeConverter
    fun toBitmapList(data: String): List<Bitmap> {
        return data.split(",").map { toBitmap(it) }
    }

    @TypeConverter
    fun fromMemoryType(type: MemoryType): String = type.name

    @TypeConverter
    fun toMemoryType(name: String): MemoryType = MemoryType.valueOf(name)
}
