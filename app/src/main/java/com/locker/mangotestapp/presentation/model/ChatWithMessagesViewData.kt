package com.locker.mangotestapp.presentation.model

data class ChatWithMessagesViewData(
    val username: String,
    val messages: List<MessageViewData>
)
