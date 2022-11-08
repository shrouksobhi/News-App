package com.shrouk.newsapp.dataSource.localDataSource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shrouk.newsapp.model.Articles

@Dao
interface Dao {
    @Insert
    suspend fun saveNews(articles: Articles)
    @Query("SELECT * FROM news_Table")
    fun showNews():LiveData<List<Articles>>

}