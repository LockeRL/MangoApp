package com.locker.mangotestapp.data.service

import com.locker.mangotestapp.datasource.remote.model.RefreshTokenRequest

interface ITokenService {
    suspend fun refreshToken(body: RefreshTokenRequest): String?
}
