package com.locker.mangotestapp.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.locker.mangotestapp.datasource.local.db.dao.TokensDao
import com.locker.mangotestapp.datasource.local.db.entity.TokensEntity

@Database(
    version = 1,
    entities = [TokensEntity::class],
    exportSchema = true
)
abstract class MangoDatabase : RoomDatabase() {
    abstract fun tokensDao(): TokensDao
}
