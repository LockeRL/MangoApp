package com.locker.mangotestapp.datasource.local.db.defaults

object MangoDBDefaults {
    const val DB_NAME = "mango_db"

    object TokensDBDefaults {
        const val TOKENS_ENTITY_TABLE_NAME = "tokens"

        const val ACCESS_TOKEN_COLUMN_NAME = "access_token"
        const val REFRESH_TOKEN_COLUMN_NAME = "refresh_token"
    }
}
