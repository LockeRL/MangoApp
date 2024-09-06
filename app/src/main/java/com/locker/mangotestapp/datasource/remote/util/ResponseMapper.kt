package com.locker.mangotestapp.datasource.remote.util

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> HttpResponse.toDto(): T? =
    try {
        body<T>()
    } catch (_: Exception) {
        null
    }
