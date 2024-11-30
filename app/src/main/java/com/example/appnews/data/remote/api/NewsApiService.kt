package com.example.appnews.data.remote.api

import com.example.appnews.data.remote.api.model.NewsResponse
import retrofit2.http.GET

interface NewsApiService {
    @GET("feed/g1")
    suspend fun getNews(): NewsResponse
}