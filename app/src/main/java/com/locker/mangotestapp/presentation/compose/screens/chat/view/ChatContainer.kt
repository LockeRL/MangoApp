package com.locker.mangotestapp.presentation.compose.screens.chat.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.locker.mangotestapp.presentation.compose.theme.Size20
import com.locker.mangotestapp.presentation.compose.theme.Space16
import com.locker.mangotestapp.presentation.compose.theme.Space4
import com.locker.mangotestapp.presentation.compose.theme.Space8
import com.locker.mangotestapp.presentation.compose.theme.Typography
import com.locker.mangotestapp.presentation.model.ChatViewData

@Composable
fun ChatContainer(
    chatInfo: ChatViewData,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .padding(Space8)
    ) {
        Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = null,
            modifier = Modifier
                .padding(end = Space8)
                .clip(CircleShape)
                .fillMaxHeight()
                .aspectRatio(1f)

        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Text(
                text = chatInfo.username,
                modifier = Modifier.padding(bottom = Space4)
            )

            Text(
                text = chatInfo.lastMessage,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = Space16)
            )
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = chatInfo.lastMessageTime,
                color = Color.Gray
            )

            Box(
                modifier = Modifier
                    .height(Size20)
                    .widthIn(min = Size20)
                    .clip(RoundedCornerShape(50))
                    .background(Color.Gray)
            ) {
                Text(
                    text = chatInfo.unreadMessagesCount.toString(),
                    color = Color.White,
                    style = Typography.bodySmall,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = Space4)
                )
            }
        }
    }
}

@Preview
@Composable
fun ChatContainerPreview() {
    ChatContainer(
        chatInfo = ChatViewData(
            chatId = "1",
            username = "Mango",
            lastMessage = "dsjkhfsdjkhfksjdhfjkhsdjkfhsdjkfhsdjk",
            lastMessageTime = "12:45",
            unreadMessagesCount = 1223
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
