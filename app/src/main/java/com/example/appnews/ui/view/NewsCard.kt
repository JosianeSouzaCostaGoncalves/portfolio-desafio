package com.example.appnews.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun NewsCard(
    chapeu: String?,
    title: String,
    description: String,
    time: String,
    image: String?,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp)
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (!chapeu.isNullOrBlank()) {
                Text(
                    text = chapeu,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            image?.let {
                Spacer(modifier = Modifier.height(4.dp))
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.body2,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = time,
                style = MaterialTheme.typography.caption,
                color = Color.Gray
            )
        }
    }
}