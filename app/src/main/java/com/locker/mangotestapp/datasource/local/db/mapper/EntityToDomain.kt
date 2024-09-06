package com.locker.mangotestapp.datasource.local.db.mapper

import com.locker.mangotestapp.datasource.local.db.entity.TokensEntity
import com.locker.mangotestapp.domain.model.Tokens

fun TokensEntity.toDomain(): Tokens = Tokens(
    accessToken = accessToken,
    refreshToken = refreshToken
)
