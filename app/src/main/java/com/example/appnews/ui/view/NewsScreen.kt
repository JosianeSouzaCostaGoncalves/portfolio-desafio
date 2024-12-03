package com.example.appnews.ui.view

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
    val newsResponse = viewModel.newsLive.observeAsState()
    var isRefreshing by viewModel.isRefreshing

    LaunchedEffect(Unit) {
        viewModel.getNews(uri)
    }

    val newsList = newsResponse.value?.feed?.falkor?.items
        ?.filter { it.type?.lowercase() in listOf("basico", "materia") }
        ?: emptyList()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                isRefreshing = true
                viewModel.getNews(uri)
            }
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(newsList) { item ->
                    NewsCard(
                        chapeu = item.content?.chapeu?.label ?: "Sem Chapeu",
                        title = item.content.title,
                        description = item.content.section,
                        time = item.metadata,
                        image = item.content.image?.sizes?.sizeL?.url,
                        onClick = {
                            val encodedUrl = Uri.encode(item.content.url)
                            navController.navigate("web_view_screen/$encodedUrl")
                        }
                    )
                }
            }
        }
    }
}