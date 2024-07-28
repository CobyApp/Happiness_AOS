package com.coby.happiness.data.repository

import com.coby.happiness.data.dao.MemoryDao
import com.coby.happiness.data.entity.MemoryEntity
import java.util.UUID
import javax.inject.Inject

class MemoryRepository @Inject constructor(
    private val memoryDao: MemoryDao
) {
    suspend fun getAllMemories() = memoryDao.getAllMemories()
    suspend fun getMemoryById(id: UUID) = memoryDao.getMemoryById(id)
    suspend fun insertMemory(memory: MemoryEntity) = memoryDao.insertMemory(memory)
    suspend fun updateMemory(memory: MemoryEntity) = memoryDao.updateMemory(memory)
    suspend fun deleteMemory(memory: MemoryEntity) = memoryDao.deleteMemory(memory)
}