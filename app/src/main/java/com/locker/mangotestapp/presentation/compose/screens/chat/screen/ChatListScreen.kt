package com.locker.mangotestapp.presentation.compose.screens.chat.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.locker.mangotestapp.R
import com.locker.mangotestapp.presentation.compose.screens.chat.view.ChatContainer
import com.locker.mangotestapp.presentation.compose.screens.chat.viewmodel.ChatsViewModel
import com.locker.mangotestapp.presentation.compose.theme.DividerSize
import com.locker.mangotestapp.presentation.compose.theme.Size72
import com.locker.mangotestapp.presentation.compose.theme.Space8
import com.locker.mangotestapp.presentation.compose.theme.Typography
import com.locker.mangotestapp.presentation.model.ChatViewData
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatListScreen(
    chats: List<ChatViewData>,
    onChatClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.chats),
            style = Typography.titleLarge,
            modifier = Modifier.padding(vertical = Space8)
        )
        HorizontalDivider(thickness = DividerSize, color = Color.Gray)

        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = modifier
        ) {
            items(chats) { chat ->
                ChatContainer(
                    chatInfo = chat,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Size72)
                        .clickable {
                            onChatClick(chat.chatId)
                        }
                )
                HorizontalDivider()
            }
        }
    }

}

@Composable
fun ChatListScreen(
    onChatClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    chatsViewModel: ChatsViewModel = koinViewModel()
) {
    val chats by chatsViewModel.testChatList.collectAsState()

    ChatListScreen(
        chats = chats,
        onChatClick = onChatClick,
        modifier = modifier
    )
}

@Preview
@Composable
fun ChatListPreview() {
    val chats = List(20) { index ->
        ChatViewData(
            chatId = index.toString(),
            username = "Mango",
            lastMessage = "dsjkhfsdjkhfksjdhfjkhsdjkfhsdjkfhsdjk",
            lastMessageTime = "12:45",
            unreadMessagesCount = 1223
        )
    }
    ChatListScreen(
        chats = chats,
        onChatClick = {},
        modifier = Modifier.fillMaxSize()
    )
}
