package com.example.appnews.data.remote.api

import com.example.appnews.data.remote.api.model.DataResponse
import retrofit2.http.GET

interface NewsApiService {
    @GET("feed/g1")
    suspend fun getRecentNews(): DataResponse
}