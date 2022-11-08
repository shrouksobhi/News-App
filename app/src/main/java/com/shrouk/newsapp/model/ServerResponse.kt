package com.shrouk.cv.model

import com.shrouk.newsapp.model.Status

data class ServerResponse<T>(val status: String, val articles: T?,
                             var totalResults: Int)