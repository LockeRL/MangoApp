package com.locker.mangotestapp.presentation.compose.screens.chat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locker.mangotestapp.presentation.model.ChatWithMessagesViewData
import com.locker.mangotestapp.presentation.model.MessageViewData
import com.locker.mangotestapp.presentation.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatWithMessagesViewModel : ViewModel() {
    private var chatId: MutableStateFlow<String?> = MutableStateFlow(null)

    var message by mutableStateOf("")
        private set

    val chatInfo: StateFlow<ChatWithMessagesViewData?> = chatId.filterNotNull().flatMapLatest { id ->
        abstractDbFun(id)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val testChatInfo: StateFlow<ChatWithMessagesViewData?> = MutableStateFlow(
        ChatWithMessagesViewData(
            username = "user",
            messages = List(40) { index ->
                MessageViewData(
                    message = "jskdhfsfhskdfhsdfkshdflsfhsdksdfgdfhfdghfhfdhdfjsl;sdflfsjflksfjskfsfsflsdfkksdlfjsfjlsd",
                    user = if (index % 2 == 1) User.OWNER else User.OPPONENT
                )

            }
        )
    )

    fun setNewMessage(msg: String) {
        message = msg
    }

    fun abstractDbFun(id: String): Flow<ChatWithMessagesViewData?> = flow { }

    fun setChatId(id: String) {
        chatId.value = id
    }

    fun sendMessage() {
        val isSuccess = true
        viewModelScope.launch {
            if (isSuccess)
                message = ""
        }
    }
}
