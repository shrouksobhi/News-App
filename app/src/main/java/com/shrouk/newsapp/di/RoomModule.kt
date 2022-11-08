package com.shrouk.newsapp.di

import android.content.Context
import androidx.room.Room
import com.shrouk.newsapp.dataSource.localDataSource.Dao
import com.shrouk.newsapp.dataSource.localDataSource.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext applicationContext: Context): NewsDataBase {
        return Room.databaseBuilder(
            applicationContext,
            NewsDataBase::class.java,
            "news_Table"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: NewsDataBase): Dao {
        return newsDatabase.newsDatabaseDao()
    }
}