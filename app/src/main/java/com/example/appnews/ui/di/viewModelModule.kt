package com.example.appnews.ui.di

import com.example.appnews.viewModel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        NewsViewModel(get())
    }
}