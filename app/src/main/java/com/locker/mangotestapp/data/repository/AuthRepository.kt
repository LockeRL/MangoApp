package com.locker.mangotestapp.data.repository

import com.locker.mangotestapp.data.service.ITokensDaoService
import com.locker.mangotestapp.data.service.IAuthService
import com.locker.mangotestapp.datasource.remote.mapper.toDomain
import com.locker.mangotestapp.datasource.remote.mapper.toDto
import com.locker.mangotestapp.domain.model.CheckAuthCode
import com.locker.mangotestapp.domain.model.RegisterUser
import com.locker.mangotestapp.domain.model.SendAuthCode
import com.locker.mangotestapp.domain.repository.IAuthRepository

class AuthRepository(
    private val tokensDaoService: ITokensDaoService,
    private val tokensNetworkService: IAuthService
) : IAuthRepository {
    override suspend fun sendAuthCode(body: SendAuthCode): Boolean =
        tokensNetworkService.getAuthCode(body.toDto())?.isSuccess ?: false

    override suspend fun checkAuthCode(body: CheckAuthCode): Boolean {
        val tokens = tokensNetworkService.checkAuthCode(body.toDto())
        if (tokens != null)
            tokensDaoService.insertTokens(tokens.toDomain())
        return tokens?.exists ?: false
    }

    override suspend fun registerUser(body: RegisterUser): Boolean {
       val tokens = tokensNetworkService.registerUser(body.toDto())
        if (tokens != null)
            tokensDaoService.insertTokens(tokens.toDomain())
        return tokens?.exists ?: false
    }
}
