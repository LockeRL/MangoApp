package com.locker.mangotestapp.datasource.remote.service

import com.locker.mangotestapp.BuildConfig
import com.locker.mangotestapp.data.service.ITokenService
import com.locker.mangotestapp.datasource.remote.model.RefreshTokenRequest
import com.locker.mangotestapp.datasource.remote.model.UserTokensDto
import com.locker.mangotestapp.datasource.remote.util.toDto
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TokenService(
    private val client: HttpClient,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ITokenService {
    override suspend fun refreshToken(body: RefreshTokenRequest): String? =
        withContext(dispatcher) {
            val response = client.post("${BuildConfig.BASE_URL}/$REFRESH_TOKEN") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            response.toDto<UserTokensDto>()?.accessToken
        }

    private companion object {
        const val REFRESH_TOKEN = "refresh-token/"
    }
}
