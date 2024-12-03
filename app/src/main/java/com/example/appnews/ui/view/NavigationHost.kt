package com.example.appnews.ui.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "news_screen"
    ) {
        composable("news_screen") {
            NewsScreen(
                navController = navController,
                uri = PRODUCT_G1
            )
        }
        composable("economy_screen") {
            NewsScreen(
                navController = navController,
                uri = PRODUCT_ECONOMY
            )
        }
        composable("menu_screen") {
            MenuScreen(navController = navController)
        }
        composable(
            route = "web_view_screen/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            WebViewScreen(navController = navController, url = url)
        }
    }
}

private const val PRODUCT_G1 = "g1"
private const val PRODUCT_ECONOMY = "https://g1.globo.com/economia/agronegocios"