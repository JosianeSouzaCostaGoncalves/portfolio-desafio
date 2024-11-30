package com.example.appnews.data.remote.repository

import com.example.appnews.data.remote.api.NewsApiService

class FeedRepository(private val newsApiService: NewsApiService) {
    suspend fun getData() = newsApiService.getRecentNews()
}
