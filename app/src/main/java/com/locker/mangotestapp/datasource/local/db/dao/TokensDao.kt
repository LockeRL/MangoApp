package com.locker.mangotestapp.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.locker.mangotestapp.datasource.local.db.entity.TokensEntity

@Dao
interface TokensDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTokens(tokens: TokensEntity)

    @Query("update tokens set access_token = (:token)")
    suspend fun updateAccessToken(token: String)

    @Query("select * from tokens limit 1")
    suspend fun getTokens(): TokensEntity?
}
