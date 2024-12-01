package com.example.appnews.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.data.remote.api.model.NewsResponse
import com.example.appnews.data.remote.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {
    private val _newsLive = MutableLiveData<NewsResponse>()
    val newsLive: LiveData<NewsResponse> = _newsLive

    var isRefreshing = mutableStateOf(false)
        private set

    fun getNews() {
        isRefreshing.value = true
        viewModelScope.launch {
            try {
                val news = repository.getNews()
                _newsLive.value = news
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isRefreshing.value = false
            }
        }
    }
}