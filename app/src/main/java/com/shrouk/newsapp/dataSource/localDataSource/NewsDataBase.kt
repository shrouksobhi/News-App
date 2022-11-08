package com.shrouk.newsapp.dataSource.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shrouk.newsapp.model.Articles

@Database(entities =[Articles::class] , version = 1 ,exportSchema = false)
abstract class NewsDataBase:RoomDatabase() {
    abstract fun newsDatabaseDao(): Dao

}