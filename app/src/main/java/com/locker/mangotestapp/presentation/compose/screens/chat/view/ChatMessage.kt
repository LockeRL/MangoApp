package com.locker.mangotestapp.presentation.compose.screens.chat.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.locker.mangotestapp.presentation.compose.theme.DefaultShape
import com.locker.mangotestapp.presentation.compose.theme.Space16
import com.locker.mangotestapp.presentation.compose.theme.Space8
import com.locker.mangotestapp.presentation.model.MessageViewData
import com.locker.mangotestapp.presentation.model.User

@Composable
fun ChatMessage(
    message: MessageViewData,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
    ) {
        Spacer(
            modifier = when (message.user) {
                User.OPPONENT -> Modifier
                User.OWNER -> Modifier.weight(1f)
            }
        )

        Box(
            modifier = when (message.user) {
                User.OPPONENT -> Modifier.padding(end = Space16)
                User.OWNER -> Modifier.padding(start = Space16)
            }
                .clip(DefaultShape)
                .background(
                    when (message.user) {
                        User.OPPONENT -> Color.Red
                        User.OWNER -> Color.Green
                    }
                )
                .padding(Space8)
        ) {
            Text(
                text = message.message
            )
        }

        Spacer(
            modifier = when (message.user) {
                User.OPPONENT -> Modifier.weight(1f)
                User.OWNER -> Modifier
            }
        )
    }
}
