package com.example.appnews.data.remote.api.model

data class Content(
    val chapeu: Chapeu,
    val identifier: String,
    val image: Any,
    val section: String,
    val title: String,
    val type: String,
    val url: String,
    val video: Any
)