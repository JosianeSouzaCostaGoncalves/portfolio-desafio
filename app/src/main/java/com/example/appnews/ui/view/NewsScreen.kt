package com.example.appnews.ui.view

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.appnews.viewModel.NewsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsScreen(
    navController: NavHostController,
    uri: String,
    viewModel: NewsViewModel = koinViewModel()
) {
    var isRefreshing by remember {
        mutableStateOf(false)
    }

    val lazyPagingItems = viewModel.getPagedNews(uri).collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.getPagedNews(uri)
    }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        state = swipeRefreshState,
        onRefresh = {
            isRefreshing = true
            viewModel.getPagedNews(uri)
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(lazyPagingItems.itemCount) { index ->
                val newsResponse = lazyPagingItems[index]
                val newsList = newsResponse?.feed?.falkor?.items
                    ?.filter { it.type?.lowercase() in listOf("basico", "materia") }
                    ?: emptyList()

                newsList.forEach { item ->
                    NewsCard(
                        chapeu = item.content?.chapeu?.label ?: "",
                        title = item.content?.title ?: "",
                        description = item.content?.summary ?: "",
                        time = item.metadata ?: "",
                        image = item.content?.image?.sizes?.sizeL?.url,
                        onClick = {
                            val encodedUrl = Uri.encode(item.content?.url)
                            navController.navigate("web_view_screen/$encodedUrl")
                        }
                    )
                }
            }
            lazyPagingItems.apply {
                when (
                    loadState.refresh
                ) {
                    is LoadState.NotLoading, is LoadState.Error -> {
                        isRefreshing = false
                    }

                    is LoadState.Loading -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator(
                                    color = MaterialTheme.colorScheme.primary,
                                    strokeWidth = 4.dp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}