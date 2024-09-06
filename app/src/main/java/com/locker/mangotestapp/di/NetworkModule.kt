package com.locker.mangotestapp.di

import com.locker.mangotestapp.data.service.IAuthService
import com.locker.mangotestapp.data.service.ITokenService
import com.locker.mangotestapp.datasource.remote.service.AuthService
import com.locker.mangotestapp.datasource.remote.service.TokenService
import org.koin.dsl.module

val networkModule = module {
    factory<IAuthService> { AuthService(get()) }
    factory<ITokenService> { TokenService(get()) }
}
