package com.coby.happiness.data.dao

import androidx.room.*
import com.coby.happiness.data.entity.BunchEntity
import java.util.UUID

@Dao
interface BunchDao {
    @Query("SELECT * FROM bunches")
    suspend fun getAllBunches(): List<BunchEntity>

    @Query("SELECT * FROM bunches WHERE id = :id")
    suspend fun getBunchById(id: UUID): BunchEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBunch(bunch: BunchEntity)

    @Update
    suspend fun updateBunch(bunch: BunchEntity)

    @Delete
    suspend fun deleteBunch(bunch: BunchEntity)

    @Query("DELETE FROM bunches")
    suspend fun deleteAllBunches()
}
