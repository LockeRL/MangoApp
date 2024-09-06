package com.locker.mangotestapp.datasource.remote.mapper

import com.locker.mangotestapp.datasource.remote.model.UserTokensDto
import com.locker.mangotestapp.domain.model.Tokens

fun UserTokensDto.toDomain(): Tokens = Tokens(
    accessToken = accessToken,
    refreshToken = refreshToken
)
