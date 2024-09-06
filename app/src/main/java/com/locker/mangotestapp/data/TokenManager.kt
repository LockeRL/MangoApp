package com.locker.mangotestapp.data

import com.locker.mangotestapp.data.service.ITokenService
import com.locker.mangotestapp.data.service.ITokensDaoService
import com.locker.mangotestapp.datasource.remote.mapper.toRefreshToken
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TokenManager(
    private val tokenService: ITokenService,
    private val tokensDaoService: ITokensDaoService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun responseWithTokenCheck(response: HttpResponse): HttpResponse? =
        withContext(dispatcher) {
            val tokens = tokensDaoService.getTokens() ?: return@withContext null
            val responseWithHeaders = responseWithHeader(response, tokens.accessToken)

            when (responseWithHeaders.status) {
                HttpStatusCode.Unauthorized -> {
                    val newToken = tokenService.refreshToken(tokens.toRefreshToken())
                        ?: return@withContext null
                    tokensDaoService.updateAccessToken(newToken)

                    responseWithHeader(response, newToken)
                }

                HttpStatusCode.OK -> response
                else -> null
            }
        }

    private fun responseWithHeader(response: HttpResponse, token: String): HttpResponse =
        response.apply {
            headers {
                append(AUTH_HEADER, "$BEARER_HEADER $token")
            }
        }

    private companion object {
        const val AUTH_HEADER = "Authorization"
        const val BEARER_HEADER = "Bearer"
    }
}
