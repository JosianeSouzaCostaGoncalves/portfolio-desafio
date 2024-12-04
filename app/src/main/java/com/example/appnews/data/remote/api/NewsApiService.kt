package com.example.appnews.data.remote.api

import com.example.appnews.data.remote.api.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApiService {
    @GET("feed/{uri}")
    suspend fun getNews(
        @Path("uri")
        uri: String
    ): NewsResponse

    @GET("feed/page/g1/{id}/{page}")
    suspend fun getFeeds(
        @Path("id")
        id: String,
        @Path("page")
        page: Int,
    ): NewsResponse
}