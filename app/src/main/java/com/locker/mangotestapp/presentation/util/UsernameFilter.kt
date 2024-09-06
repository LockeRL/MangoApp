package com.locker.mangotestapp.presentation.util

const val USERNAME_PATTERN = "^[A-Za-z0-9-_]+\$"
fun filterUsername(username: String): Boolean = username.matches(Regex(USERNAME_PATTERN))
