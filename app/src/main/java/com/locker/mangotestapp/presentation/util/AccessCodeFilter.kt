package com.locker.mangotestapp.presentation.util

const val MAX_CODE_LENGTH = 6

fun String.matchesUserCode(): Boolean = all { it.isDigit() } && length <= MAX_CODE_LENGTH
