package com.locker.mangotestapp.data.service

import com.locker.mangotestapp.datasource.remote.model.AccessCodeRequestDto
import com.locker.mangotestapp.datasource.remote.model.AuthSendPhoneDto
import com.locker.mangotestapp.datasource.remote.model.CheckAuthCodeRequestDto
import com.locker.mangotestapp.datasource.remote.model.RegisterUserRequestDto
import com.locker.mangotestapp.datasource.remote.model.UserTokensDto


interface IAuthService {
    suspend fun getAuthCode(body: AccessCodeRequestDto): AuthSendPhoneDto?

    suspend fun checkAuthCode(body: CheckAuthCodeRequestDto): UserTokensDto?

    suspend fun registerUser(body: RegisterUserRequestDto): UserTokensDto?
}
