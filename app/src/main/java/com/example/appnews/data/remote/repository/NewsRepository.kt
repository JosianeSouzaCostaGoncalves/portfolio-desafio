package com.example.appnews.data.remote.repository

import com.example.appnews.data.remote.api.NewsApiService
import com.example.appnews.data.remote.api.model.NewsResponse

class NewsRepository(
    private val newsApiService: NewsApiService
) {
   suspend fun getNews(page: Int): NewsResponse {
       return newsApiService.getNews(page)
   }
}
