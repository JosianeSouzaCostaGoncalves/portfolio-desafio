package com.example.appnews.data.network

import com.example.appnews.data.remote.api.NewsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkProviders {
    private const val BASE_URL = "https://native-leon.globo.com/"

    fun providerRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}