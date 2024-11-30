package com.example.appnews.data.di

import com.example.appnews.data.remote.repository.NewsRepository
import com.example.appnews.data.network.NetworkProviders
import com.example.appnews.data.remote.api.NewsApiService
import org.koin.dsl.module


val serviceModule = module {
    single { NetworkProviders.providerRetrofit().create(NewsApiService::class.java) }
    single { NewsRepository(get()) }
}