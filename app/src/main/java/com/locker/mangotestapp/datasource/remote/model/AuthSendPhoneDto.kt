package com.locker.mangotestapp.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthSendPhoneDto(
    @SerialName("is_success")
    val isSuccess: Boolean
)
