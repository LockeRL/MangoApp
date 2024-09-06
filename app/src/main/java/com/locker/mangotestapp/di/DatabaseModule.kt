package com.locker.mangotestapp.di

import androidx.room.Room
import com.locker.mangotestapp.data.service.ITokensDaoService
import com.locker.mangotestapp.datasource.local.db.MangoDatabase
import com.locker.mangotestapp.datasource.local.db.defaults.MangoDBDefaults
import com.locker.mangotestapp.datasource.local.db.service.TokensDaoService
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single<MangoDatabase> {
        Room
            .databaseBuilder(
                androidApplication(),
                MangoDatabase::class.java,
                MangoDBDefaults.DB_NAME
            )
            .build()
    }

    factory<ITokensDaoService> {
        TokensDaoService(
            get<MangoDatabase>().tokensDao()
        )
    }
}
