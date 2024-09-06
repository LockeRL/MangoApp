package com.locker.mangotestapp.di

import com.locker.mangotestapp.data.repository.AuthRepository
import com.locker.mangotestapp.domain.repository.IAuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<IAuthRepository> { AuthRepository(get(), get()) }
}
