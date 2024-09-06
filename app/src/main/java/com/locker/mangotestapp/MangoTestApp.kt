package com.locker.mangotestapp

import android.app.Application
import com.locker.mangotestapp.di.databaseModule
import com.locker.mangotestapp.di.httpClientModule
import com.locker.mangotestapp.di.networkModule
import com.locker.mangotestapp.di.repositoryModule
import com.locker.mangotestapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MangoTestApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)

            modules(
                viewModelModule,
                databaseModule,
                httpClientModule,
                networkModule,
                repositoryModule
            )
        }
    }
}
