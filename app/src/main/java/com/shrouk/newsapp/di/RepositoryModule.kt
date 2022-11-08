package com.shrouk.newsapp.di

import android.content.SharedPreferences
import com.shrouk.newsapp.dataSource.Repository
import com.shrouk.newsapp.dataSource.localDataSource.Dao
import com.shrouk.newsapp.dataSource.remoteDataSource.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideApiInerface(retrofit: Retrofit)=retrofit.create(Api::class.java)


    @Provides
    @Singleton
    fun provideRepository(apiInterface: Api,newsDao: Dao)
            = Repository(apiInterface, newsDao)
}