package com.example.appnews.ui.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AppHeader() {
    Text(
        text = "Aplicativo News",
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
}