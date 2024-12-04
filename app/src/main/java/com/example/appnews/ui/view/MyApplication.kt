package com.example.appnews.ui.view

import android.app.Application
import com.example.appnews.data.di.serviceModule
import com.example.appnews.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(serviceModule)
            modules(viewModelModule)
        }
    }
}