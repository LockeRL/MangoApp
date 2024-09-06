package com.locker.mangotestapp.di

import com.locker.mangotestapp.presentation.compose.screens.authscreen.viewmodel.AuthViewModel
import com.locker.mangotestapp.presentation.compose.screens.chat.viewmodel.ChatWithMessagesViewModel
import com.locker.mangotestapp.presentation.compose.screens.chat.viewmodel.ChatsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { ChatsViewModel() }
    viewModel { ChatWithMessagesViewModel() }
}
