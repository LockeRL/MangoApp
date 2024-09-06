package com.locker.mangotestapp.datasource.remote.model

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserTokensDto @OptIn(ExperimentalSerializationApi::class) constructor(
    @SerialName("refresh_token")
    val refreshToken: String,
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("is_user_exists")
    @EncodeDefault
    val exists: Boolean = true
)
