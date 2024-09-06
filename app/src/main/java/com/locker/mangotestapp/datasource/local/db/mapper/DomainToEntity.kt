package com.locker.mangotestapp.datasource.local.db.mapper

import com.locker.mangotestapp.datasource.local.db.entity.TokensEntity
import com.locker.mangotestapp.domain.model.Tokens

fun Tokens.toEntity(): TokensEntity = TokensEntity(
    accessToken = accessToken,
    refreshToken = refreshToken
)
