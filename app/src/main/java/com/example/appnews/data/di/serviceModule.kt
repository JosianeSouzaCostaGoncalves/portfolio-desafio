package com.example.appnews.data.di

import com.example.appnews.data.remote.repository.FeedRepository
import com.example.appnews.data.network.NetworkProviders
import com.example.appnews.data.remote.api.NewsApiService
import org.koin.dsl.module


val appModule = module {
    single { NetworkProviders.providerRetrofit().create(NewsApiService::class.java) }
    single { FeedRepository(get()) }
}