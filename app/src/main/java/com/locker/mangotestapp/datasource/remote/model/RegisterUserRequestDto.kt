package com.locker.mangotestapp.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserRequestDto(
    val phone: String,
    val name: String,
    val username: String
)
