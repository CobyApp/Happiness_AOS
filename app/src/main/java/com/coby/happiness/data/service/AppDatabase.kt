package com.coby.happiness.data.service

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coby.happiness.data.dao.BunchDao
import com.coby.happiness.data.dao.MemoryDao
import com.coby.happiness.data.entity.BunchEntity
import com.coby.happiness.data.entity.MemoryEntity
import java.util.UUID

@Database(entities = [MemoryEntity::class, BunchEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoryDao(): MemoryDao
    abstract fun bunchDao(): BunchDao
}
