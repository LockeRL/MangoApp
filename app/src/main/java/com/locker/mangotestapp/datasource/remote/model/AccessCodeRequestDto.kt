package com.locker.mangotestapp.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AccessCodeRequestDto(
    val phone: String
)
