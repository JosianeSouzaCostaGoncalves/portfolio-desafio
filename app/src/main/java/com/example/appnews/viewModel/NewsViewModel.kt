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

    private var hasNextPage = true

    fun getNews(uri: String) {
        viewModelScope.launch {

            try {
                val newsResponse = repository.getNews(uri)
                _newsLive.value = newsResponse
                hasNextPage = newsResponse.feed.falkor.items.isNotEmpty()
                isRefreshing.value = false

            } catch (e: Exception) {
                isRefreshing.value = false
                e.printStackTrace()
            }
        }
    }
}