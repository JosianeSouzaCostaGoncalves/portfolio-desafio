package com.example.appnews.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.appnews.data.remote.api.NewsApiService
import com.example.appnews.data.remote.api.model.NewsResponse
import kotlinx.coroutines.flow.Flow

class NewsRepository(
    private val newsApiService: NewsApiService
) {
    fun getNewsPaged(uri: String): Flow<PagingData<NewsResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(newsApiService, uri) }
        ).flow
    }
}
