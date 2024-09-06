package com.locker.mangotestapp.datasource.local.db.service

import com.locker.mangotestapp.data.service.ITokensDaoService
import com.locker.mangotestapp.datasource.local.db.dao.TokensDao
import com.locker.mangotestapp.datasource.local.db.mapper.toDomain
import com.locker.mangotestapp.datasource.local.db.mapper.toEntity
import com.locker.mangotestapp.domain.model.Tokens
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TokensDaoService(
    private val tokensDao: TokensDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ITokensDaoService {
    override suspend fun insertTokens(tokens: Tokens) {
        withContext(dispatcher) {
            tokensDao.insertTokens(tokens.toEntity())
        }
    }

    override suspend fun updateAccessToken(token: String) {
        withContext(dispatcher) {
            tokensDao.updateAccessToken(token)
        }
    }

    override suspend fun getTokens(): Tokens? =
        withContext(dispatcher) {
            tokensDao.getTokens()?.toDomain()
        }

}
