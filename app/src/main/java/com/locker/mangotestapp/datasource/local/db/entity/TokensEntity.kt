package com.locker.mangotestapp.datasource.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.locker.mangotestapp.datasource.local.db.defaults.MangoDBDefaults

@Entity(tableName = MangoDBDefaults.TokensDBDefaults.TOKENS_ENTITY_TABLE_NAME)
data class TokensEntity(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(MangoDBDefaults.TokensDBDefaults.ACCESS_TOKEN_COLUMN_NAME)
    val accessToken: String,
    @ColumnInfo(MangoDBDefaults.TokensDBDefaults.REFRESH_TOKEN_COLUMN_NAME)
    val refreshToken: String
)
