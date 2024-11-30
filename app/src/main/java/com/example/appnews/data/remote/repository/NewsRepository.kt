package com.example.appnews.data.remote.repository

import com.example.appnews.data.remote.api.NewsApiService

class NewsRepository(
    private val newsApiService: NewsApiService
) {
    suspend fun getNews() = newsApiService.getNews()
}
