package com.locker.mangotestapp.presentation.model

data class ChatViewData(
    val chatId: String,
    val username: String,
    val lastMessage: String,
    val lastMessageTime: String,
    val unreadMessagesCount: Int
)
