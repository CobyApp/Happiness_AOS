package com.coby.happiness.data.repository

import com.coby.happiness.data.dao.BunchDao
import com.coby.happiness.data.entity.BunchEntity
import java.util.UUID
import javax.inject.Inject

class BunchRepository @Inject constructor(
    private val bunchDao: BunchDao
) {
    suspend fun getAllBunches() = bunchDao.getAllBunches()
    suspend fun getBunchById(id: UUID) = bunchDao.getBunchById(id)
    suspend fun insertBunch(bunch: BunchEntity) = bunchDao.insertBunch(bunch)
    suspend fun updateBunch(bunch: BunchEntity) = bunchDao.updateBunch(bunch)
    suspend fun deleteBunch(bunch: BunchEntity) = bunchDao.deleteBunch(bunch)
    suspend fun deleteAllBunches() = bunchDao.deleteAllBunches()
}