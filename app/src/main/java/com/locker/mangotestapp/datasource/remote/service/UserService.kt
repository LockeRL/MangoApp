package com.locker.mangotestapp.datasource.remote.service

import com.locker.mangotestapp.BuildConfig
import com.locker.mangotestapp.data.TokenManager
import com.locker.mangotestapp.datasource.remote.model.UserDto
import com.locker.mangotestapp.datasource.remote.util.toDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserService(
    private val client: HttpClient,
    private val tokenManager: TokenManager,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getUser(): UserDto? =
        withContext(dispatcher) {
            val response = tokenManager.responseWithTokenCheck(
                client.get("${BuildConfig.BASE_URL}/$ME")
            )

            response?.toDto<UserDto>()
        }

    private companion object {
        const val ME = "me/"
    }
}
