package com.locker.mangotestapp.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val name: String,
    val username: String,
    val city: String,
    val birthday: String,
    val status: String,
    val phone: String
)
