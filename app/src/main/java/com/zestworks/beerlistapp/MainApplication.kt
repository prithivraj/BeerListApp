package com.zestworks.beerlistapp

import android.app.Application
import com.zestworks.beerdetail.di.beerDetailModule
import com.zestworks.data.di.dataModule
import com.zestworks.list.di.beerListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                listOf(
                    dataModule,
                    beerListModule,
                    beerDetailModule
                )
            )
        }
    }
}