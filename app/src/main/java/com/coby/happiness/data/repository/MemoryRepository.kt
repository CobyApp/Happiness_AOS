package com.coby.happiness.data.repository

import com.coby.happiness.data.dao.MemoryDao
import com.coby.happiness.data.mapper.toMemoryEntity
import com.coby.happiness.data.mapper.toMemoryModel
import com.coby.happiness.domain.model.MemoryModel
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemoryRepository @Inject constructor(
    private val memoryDao: MemoryDao
) {
    suspend fun getAllMemories(): List<MemoryModel> {
        return withContext(Dispatchers.IO) {
            memoryDao.getAllMemories().map { it.toMemoryModel() }
        }
    }

    suspend fun getMemoryById(id: UUID): MemoryModel? {
        return withContext(Dispatchers.IO) {
            memoryDao.getMemoryById(id)?.toMemoryModel()
        }
    }

    suspend fun insertMemory(memory: MemoryModel) {
        withContext(Dispatchers.IO) {
            memoryDao.insertMemory(memory.toMemoryEntity())
        }
    }

    suspend fun updateMemory(memory: MemoryModel) {
        withContext(Dispatchers.IO) {
            memoryDao.updateMemory(memory.toMemoryEntity())
        }
    }

    suspend fun deleteMemory(id: UUID) {
        withContext(Dispatchers.IO) {
            memoryDao.deleteMemory(memoryDao.getMemoryById(id) ?: return@withContext)
        }
    }
}
