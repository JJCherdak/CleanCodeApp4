package com.geekbrains.cleancodeapp.application

import android.app.Application
import com.geekbrains.cleancodeapp.di.application
import com.geekbrains.cleancodeapp.di.historyScreen
import com.geekbrains.cleancodeapp.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CleanCodeApp : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }


}