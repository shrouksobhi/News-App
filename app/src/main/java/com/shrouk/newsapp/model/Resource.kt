package com.shrouk.cv.model

import com.shrouk.newsapp.model.Status


data class Resource<out T>(val status: Status, val articles: T?,
                           var totalResults: Int) {

    companion object {

        fun <T> success(articles: T?,totalResults: Int): Resource<T> {
            return Resource(Status.OK, articles,totalResults)
        }
        fun <T> error(totalResults: Int, articles: T?): Resource<T> {
            return Resource(Status.ERROR, articles, totalResults)
        }

        fun <T> loading(articles: T? = null): Resource<T> {
            return Resource(Status.LOADING, articles, 0)
        }

    }

}