package com.coby.happiness.data.repository

import com.coby.happiness.data.dao.BunchDao
import com.coby.happiness.data.dao.MemoryDao
import com.coby.happiness.data.mapper.toBunchEntity
import com.coby.happiness.data.mapper.toBunchModel
import com.coby.happiness.data.mapper.toMemoryModel
import com.coby.happiness.domain.model.BunchModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class BunchRepository @Inject constructor(
    private val bunchDao: BunchDao,
    private val memoryDao: MemoryDao
) {
    suspend fun getAllBunches(): List<Unit> {
        return withContext(Dispatchers.IO) {
            bunchDao.getAllBunches().map { bunchEntity ->
                val memories = bunchEntity.memoryIds.mapNotNull { memoryDao.getMemoryById(it)?.toMemoryModel() }
                bunchEntity.toBunchModel(memories)
            }
        }
    }

    suspend fun getBunchById(id: UUID): BunchModel? {
        return withContext(Dispatchers.IO) {
            val bunchEntity = bunchDao.getBunchById(id) ?: return@withContext null
            val memories = bunchEntity.memoryIds.mapNotNull { memoryDao.getMemoryById(it)?.toMemoryModel() }
            bunchEntity.toBunchModel(memories)
        }
    }

    suspend fun insertBunch(bunch: BunchModel) {
        withContext(Dispatchers.IO) {
            bunchDao.insertBunch(bunch.toBunchEntity())
        }
    }

    suspend fun updateBunch(bunch: BunchModel) {
        withContext(Dispatchers.IO) {
            bunchDao.updateBunch(bunch.toBunchEntity())
        }
    }

    suspend fun deleteBunch(id: UUID) {
        withContext(Dispatchers.IO) {
            bunchDao.deleteBunch(bunchDao.getBunchById(id) ?: return@withContext)
        }
    }
}
