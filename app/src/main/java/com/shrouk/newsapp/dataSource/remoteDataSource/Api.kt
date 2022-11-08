package com.shrouk.newsapp.dataSource.remoteDataSource

import com.shrouk.cv.model.ServerResponse
import com.shrouk.newsapp.model.Articles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country:String,
        @Query("apiKey") apiKey:String,
        @Query("pageSize") pageSize:Int=4
    ):Response<ServerResponse<ArrayList<Articles>>>

    @GET("/v2/everything")
    suspend fun getSearch(
        @Query("q") query:String,
        @Query("apiKey") apiKey:String,
        @Query("pageSize") pageSize:Int=10
    ):Response<ServerResponse<ArrayList<Articles>>>

    @GET("/v2/everything")
    suspend fun getEverything(
        @Query("q") query:String,
        @Query("apiKey") apiKey:String,
        @Query("pageSize") pageSize:Int=10
    ):Response<ServerResponse<ArrayList<Articles>>>
}
