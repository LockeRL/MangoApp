package com.locker.mangotestapp.datasource.remote.mapper

import com.locker.mangotestapp.datasource.remote.model.AccessCodeRequestDto
import com.locker.mangotestapp.datasource.remote.model.CheckAuthCodeRequestDto
import com.locker.mangotestapp.datasource.remote.model.RefreshTokenRequest
import com.locker.mangotestapp.datasource.remote.model.RegisterUserRequestDto
import com.locker.mangotestapp.domain.model.CheckAuthCode
import com.locker.mangotestapp.domain.model.RegisterUser
import com.locker.mangotestapp.domain.model.SendAuthCode
import com.locker.mangotestapp.domain.model.Tokens

fun SendAuthCode.toDto(): AccessCodeRequestDto = AccessCodeRequestDto(
    phone = phone
)

fun CheckAuthCode.toDto(): CheckAuthCodeRequestDto = CheckAuthCodeRequestDto(
    phone = phone,
    code = code
)

fun RegisterUser.toDto(): RegisterUserRequestDto = RegisterUserRequestDto(
    phone = phone,
    name = name,
    username = username
)

fun Tokens.toRefreshToken(): RefreshTokenRequest = RefreshTokenRequest(
    refreshToken = refreshToken
)
