package com.locker.mangotestapp.presentation.compose.screens.authscreen.state

sealed interface LoginUIState {
    data object Idle : LoginUIState
    data object Success : LoginUIState
    data object Redirect : LoginUIState
}
