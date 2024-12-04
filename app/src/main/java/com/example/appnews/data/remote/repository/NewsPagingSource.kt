package com.example.appnews.data.remote.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.appnews.data.remote.api.NewsApiService
import com.example.appnews.data.remote.api.model.NewsResponse

class NewsPagingSource(
    private val api: NewsApiService,
    private val uri: String
): PagingSource<Int, NewsResponse>() {
    private var feedId: String? = null
    private var nextPage: Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsResponse> {
        return try {
            val currentPage = params.key ?: 1

            if (currentPage == 1) {
                val newsResponse = api.getNews(uri)
                nextPage = newsResponse.feed?.falkor?.nextPage
                feedId = newsResponse.feed?.oferta
                LoadResult.Page(
                    data = listOf(newsResponse),
                    prevKey = null,
                    nextKey = if (newsResponse.feed?.falkor?.items?.isEmpty() == true) null else newsResponse.feed?.falkor?.nextPage
                )
            } else {
                val feedResponse = api.getFeeds(
                    id = feedId?: "",
                    page = currentPage
                )

                LoadResult.Page(
                    data = listOf(feedResponse),
                    prevKey = currentPage - 1,
                    nextKey = if (feedResponse.feed?.falkor?.items?.isEmpty() == true) null else feedResponse.feed?.falkor?.nextPage
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}