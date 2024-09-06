package com.locker.mangotestapp.presentation.compose.navigation.screen

sealed class AuthScreen(name: String) : Screen(name){
    data object LogInScreen : AuthScreen("Log In Screen")
    data object RegisterScreen : AuthScreen("Register Screen")
}
