package com.shrouk.newsapp.newsExt

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shrouk.cv.model.Resource
import com.shrouk.cv.model.ServerResponse
import retrofit2.Response


suspend fun <T> getResponse(
    request: suspend () -> Response<ServerResponse<T>>,
    defaultErrorMessage: Int
//: Resource<T>
): Resource<T> {
    return try {
        val result = request.invoke()
        if (result.isSuccessful) {
            val response: ServerResponse<T>? = result.body()
            //  return Resource.success(response?.data, response?.message)
            return Resource.success(response?.articles, response!!.totalResults)
        } else {
            val gson = Gson()
            val type = object : TypeToken<ServerResponse<String>?>() {}.type
            Log.e("result code", result.code().toString())
            Log.e("result Error", result.errorBody()!!.charStream().readText())
            val errorResponse: ServerResponse<String> =
                gson.fromJson(result.errorBody()!!.charStream(), type)
            Resource.error(errorResponse.totalResults, null)
        }
    } catch (e: Throwable) {
        e.printStackTrace()
        Resource.error(defaultErrorMessage, null)
    }
}