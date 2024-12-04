package com.example.appnews.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.appnews.data.remote.api.model.NewsResponse
import com.example.appnews.data.remote.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    fun getPagedNews(uri: String): Flow<PagingData<NewsResponse>> {
        return repository.getNewsPaged(uri).cachedIn(viewModelScope)
    }
}