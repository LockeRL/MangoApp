package com.locker.mangotestapp.data.service


import com.locker.mangotestapp.domain.model.Tokens

interface ITokensDaoService {
    suspend fun insertTokens(tokens: Tokens)

    suspend fun updateAccessToken(token: String)

    suspend fun getTokens(): Tokens?
}
