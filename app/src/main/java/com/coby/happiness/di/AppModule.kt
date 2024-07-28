package com.coby.happiness.di

import android.content.Context
import androidx.room.Room
import com.coby.happiness.data.dao.BunchDao
import com.coby.happiness.data.dao.MemoryDao
import com.coby.happiness.data.service.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Context): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMemoryDao(db: AppDatabase): MemoryDao = db.memoryDao()

    @Provides
    fun provideBunchDao(db: AppDatabase): BunchDao = db.bunchDao()
}
