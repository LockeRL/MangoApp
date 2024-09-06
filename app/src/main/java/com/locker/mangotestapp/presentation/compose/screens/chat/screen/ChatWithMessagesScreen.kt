package com.locker.mangotestapp.presentation.compose.screens.chat.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.locker.mangotestapp.presentation.compose.screens.chat.view.ChatMessage
import com.locker.mangotestapp.presentation.compose.screens.chat.view.EnterMessageBar
import com.locker.mangotestapp.presentation.compose.screens.chat.viewmodel.ChatWithMessagesViewModel
import com.locker.mangotestapp.presentation.compose.theme.DividerSize
import com.locker.mangotestapp.presentation.compose.theme.Space16
import com.locker.mangotestapp.presentation.compose.theme.Space8
import com.locker.mangotestapp.presentation.compose.theme.Typography
import com.locker.mangotestapp.presentation.model.ChatWithMessagesViewData
import com.locker.mangotestapp.presentation.model.MessageViewData
import com.locker.mangotestapp.presentation.model.User
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatWithMessagesScreen(
    chat: ChatWithMessagesViewData,
    message: String,
    onMessageChange: (String) -> Unit,
    onSendMessageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = chat.username,
            style = Typography.titleLarge,
            modifier = Modifier.padding(vertical = Space8)
        )
        HorizontalDivider(
            thickness = DividerSize,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = Space8)
        )

        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            reverseLayout = true,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = Space16)
        ) {
            items(chat.messages) { message ->
                ChatMessage(
                    message = message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Space8)
                )
            }
        }

        EnterMessageBar(
            message = message,
            onMessageChange = onMessageChange,
            onSendClick = onSendMessageClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ChatWithMessagesScreen(
    chatId: String,
    modifier: Modifier = Modifier,
    chatViewModel: ChatWithMessagesViewModel = koinViewModel()
) {
    chatViewModel.setChatId(chatId)
    val chat by chatViewModel.testChatInfo.collectAsState()

    ChatWithMessagesScreen(
        chat = chat ?: return,
        message = chatViewModel.message,
        onMessageChange = chatViewModel::setNewMessage,
        onSendMessageClick = chatViewModel::sendMessage,
        modifier = modifier
    )
}

@Preview
@Composable
fun ChatWithMessagesScreenPreview() {
    val message = MessageViewData(
        message = "jskdhfsfhskdfhsdfkshdflsfhsdksdfgdfhfdghfhfdhdfjsl;sdflfsjflksfjskfsfsflsdfkksdlfjsfjlsd",
        user = User.OWNER
    )
    val chat = ChatWithMessagesViewData(
        username = "test",
        messages = listOf(
            message,
            message.copy(user = User.OPPONENT),
            message.copy(user = User.OPPONENT),
            message.copy(user = User.OPPONENT),
            message,
            message,
            message
        )
    )
    ChatWithMessagesScreen(
        chat = chat,
        message = "ksjdlf",
        onMessageChange = {},
        onSendMessageClick = {},
        modifier = Modifier.fillMaxSize()
    )
}
