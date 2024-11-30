package com.example.appnews.viewModel

import android.util.Log
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

    fun getNews() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getNews()
                }
                _newsLive.value = response
            } catch (e: Exception) {
                e.message?.let { Log.d("bob", it) }
            }
        }
    }
}