package com.example.appnews.ui.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TabRowComponent(selectedTabIndex: Int, tabs: List<String>, onTabSelected: (Int) -> Unit) {
    TabRow(selectedTabIndex = selectedTabIndex) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = { Text(title) }
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}