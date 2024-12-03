package com.example.appnews.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.data.remote.api.model.NewsResponse
import com.example.appnews.data.remote.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {
    private val _newsLive = MutableLiveData<NewsResponse>()
    val newsLive: LiveData<NewsResponse> = _newsLive

    var isRefreshing = mutableStateOf(false)
        private set

    var isLoadingMore = mutableStateOf(false)
        private set

    private var currentPage = 1
    private var hasNextPage = true

    fun getNews(refresh: Boolean = false) {
        if (refresh) {
            currentPage = 1
            hasNextPage = true
        }

        if (isLoadingMore.value || (refresh && isRefreshing.value)) return
        if (refresh) {
            isRefreshing.value = true
        } else {
            isLoadingMore.value = true
        }

        viewModelScope.launch {
            try {
                val newsResponse = repository.getNews(currentPage)
                if (refresh) {
                    _newsLive.value = newsResponse
                } else {
                    val currentData = _newsLive.value
                    val combinedItems = (currentData?.feed?.falkor?.items ?: emptyList()) +
                            (newsResponse.feed.falkor.items ?: emptyList())

                    _newsLive.value = currentData?.copy(
                        feed = currentData.feed.copy(
                            falkor = currentData.feed.falkor.copy(items = combinedItems)
                        )
                    ) ?: newsResponse
                }

                hasNextPage = newsResponse.feed.falkor.items.isNotEmpty()
                currentPage++
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isRefreshing.value = false
                isLoadingMore.value = false
            }
        }
    }
}