package com.example.appnews.data.remote.api

import com.example.appnews.data.remote.api.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApiService {
    @GET("feed/{uri}")
    suspend fun getNews(
        @Path("uri")
        uri: String
    ): NewsResponse
}