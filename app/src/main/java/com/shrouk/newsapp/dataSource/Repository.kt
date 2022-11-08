package com.shrouk.newsapp.dataSource

import androidx.lifecycle.LiveData
import com.shrouk.cv.model.Resource
import com.shrouk.newsapp.BuildConfig
import com.shrouk.newsapp.dataSource.localDataSource.Dao
import com.shrouk.newsapp.dataSource.localDataSource.NewsDataBase
import com.shrouk.newsapp.dataSource.remoteDataSource.Api
import com.shrouk.newsapp.model.Articles
import com.shrouk.newsapp.newsExt.getResponse
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

class Repository @Inject constructor(private var apiInterface: Api,
                                     private val newsDao: Dao
) {
    val country:String="eg"
    val apiKey:String= BuildConfig.API_KEY
   val  query: String = "gold"
     suspend fun getHeadlines():Resource<ArrayList<Articles>>{
         return getResponse({apiInterface.getTopHeadlines(country,apiKey)},
         0)
     }
    suspend fun getEverything():Resource<ArrayList<Articles>>{
        return getResponse({apiInterface.getEverything(query,apiKey)},
            0)
    }
    suspend fun getSearch(query:String):Resource<ArrayList<Articles>>{
        return getResponse({apiInterface.getSearch(query,apiKey)},
            0)
    }
    suspend fun getSavedNews(): LiveData<List<Articles>>{
        return newsDao.showNews()
    }
    suspend fun saveNews(article:Articles){
        newsDao.saveNews(article)
    }


}