package com.locker.mangotestapp.domain.repository

import com.locker.mangotestapp.domain.model.CheckAuthCode
import com.locker.mangotestapp.domain.model.RegisterUser
import com.locker.mangotestapp.domain.model.SendAuthCode

interface IAuthRepository {
    suspend fun sendAuthCode(body: SendAuthCode): Boolean

    suspend fun checkAuthCode(body: CheckAuthCode): Boolean

    suspend fun registerUser(body: RegisterUser): Boolean
}
