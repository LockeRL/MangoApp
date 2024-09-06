package com.locker.mangotestapp.presentation.compose.screens.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locker.mangotestapp.presentation.model.ChatViewData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ChatsViewModel : ViewModel() {
    val chatList: StateFlow<List<ChatViewData>> = abstractDbFun().map { chatList ->
        chatList.map { chat -> chat /* Map from repository */ }.sortedBy(ChatViewData::lastMessageTime)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, EMPTY_CHATS_LIST)

    val testChatList: StateFlow<List<ChatViewData>> = MutableStateFlow(
        List(20) { index -> ChatViewData(
            chatId = index.toString(),
            username = "Mango",
            lastMessage = "dsjkhfsdjkhfksjdhfjkhsdjkfhsdjkfhsdjk",
            lastMessageTime = "12:45",
            unreadMessagesCount = 1223
        ) }
    )

    private fun abstractDbFun(): Flow<List<ChatViewData>> = flow { }

    private companion object {
        val EMPTY_CHATS_LIST = emptyList<ChatViewData>()
    }
}
