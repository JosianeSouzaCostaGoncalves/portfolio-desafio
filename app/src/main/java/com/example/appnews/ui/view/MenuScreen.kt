package com.example.appnews.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        MenuList(navController)
    }
}

data class MenuItem(
    val title: String,
    val url: String
)

object NewsMenu {
    val menuItems = listOf(
        MenuItem(
            title = "agro",
            url = "https://g1.globo.com/economia/agronegocios/"
        ),
        MenuItem(
            title = "bem estar",
            url = "https://g1.globo.com/bemestar/"
        ),
        MenuItem(
            title = "carros",
            url = "https://g1.globo.com/carros/"
        ),
        MenuItem(
            title = "ciência e saúde",
            url = "https://g1.globo.com/ciencia-e-saude/"
        ),
        MenuItem(
            title = "economia",
            url = "https://g1.globo.com/economia/"
        ),
        MenuItem(
            title = "educação",
            url = "https://g1.globo.com/educacao/"
        ),
        MenuItem(
            title = "fato ou fake",
            url = "https://g1.globo.com/fato-ou-fake/"
        )
    )
}

@Composable
fun MenuList(navController: NavController) {
    val menuItems = NewsMenu.menuItems

    Column(modifier = Modifier.padding(16.dp)) {
        menuItems.forEach { menuItem ->
            Button(
                onClick = {
                    val encodedUrl = URLEncoder.encode(menuItem.url, StandardCharsets.UTF_8.toString())
                    navController.navigate("web_view_screen/$encodedUrl")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = androidx.compose.material.ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                elevation = androidx.compose.material.ButtonDefaults.elevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                )
            ) {
                Text(
                    text = menuItem.title.uppercase(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}