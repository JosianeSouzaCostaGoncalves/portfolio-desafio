package com.example.appnews.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val tabs = listOf("Home", "Economia", "Menu")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
    ) {
        AppHeader()
        TabRowComponent(selectedTabIndex, tabs) { index ->
            selectedTabIndex = index
            when (index) {
                0 -> navController.navigate("news_screen")
                1 -> navController.navigate("economy_screen")
                2 -> navController.navigate("menu_screen")
            }
        }
        NavigationHost(navController)
    }
}