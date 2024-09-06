package com.locker.mangotestapp.datasource.remote.service

import com.locker.mangotestapp.BuildConfig
import com.locker.mangotestapp.data.service.IAuthService
import com.locker.mangotestapp.datasource.remote.model.AccessCodeRequestDto
import com.locker.mangotestapp.datasource.remote.model.AuthSendPhoneDto
import com.locker.mangotestapp.datasource.remote.model.CheckAuthCodeRequestDto
import com.locker.mangotestapp.datasource.remote.model.RegisterUserRequestDto
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

class AuthService(
    private val client: HttpClient,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IAuthService {
    override suspend fun getAuthCode(body: AccessCodeRequestDto): AuthSendPhoneDto? =
        withContext(dispatcher) {
            val response = client.post("${BuildConfig.BASE_URL}/$SEND_AUTH_CODE") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            response.toDto()
        }


    override suspend fun checkAuthCode(body: CheckAuthCodeRequestDto): UserTokensDto? =
        withContext(dispatcher) {
            val response = client.post("${BuildConfig.BASE_URL}/$CHECK_AUTH_CODE") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            response.toDto()
        }

    override suspend fun registerUser(body: RegisterUserRequestDto): UserTokensDto? =
        withContext(dispatcher) {
            val response = client.post("${BuildConfig.BASE_URL}/$REGISTER") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            response.toDto()
        }

    private companion object {
        const val SEND_AUTH_CODE = "send-auth-code/"
        const val CHECK_AUTH_CODE = "check-auth-code/"
        const val REGISTER = "register/"
    }
}
