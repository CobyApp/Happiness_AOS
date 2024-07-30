package com.coby.happiness.di

import android.content.Context
import com.coby.happiness.data.dao.BunchDao
import com.coby.happiness.data.dao.MemoryDao
import com.coby.happiness.data.service.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(appContext)
    }

    @Provides
    fun provideMemoryDao(db: AppDatabase): MemoryDao = db.memoryDao()

    @Provides
    fun provideBunchDao(db: AppDatabase): BunchDao = db.bunchDao()
}
