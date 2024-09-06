package com.locker.mangotestapp.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CheckAuthCodeRequestDto(
    val phone: String,
    val code: String
)
