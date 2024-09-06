package com.locker.mangotestapp.presentation.compose.screens.chat.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.locker.mangotestapp.R
import com.locker.mangotestapp.presentation.compose.theme.Space8

@Composable
fun EnterMessageBar(
    message: String,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(Space8)
    ) {
        TextField(
            value = message,
            onValueChange = { newMessage -> onMessageChange(newMessage) },
            modifier = Modifier
                .weight(1f)
                .padding(end = Space8),
            placeholder = { Text(text = stringResource(id = R.string.enter_message)) }
        )

        Button(
            onClick = { onSendClick() }
        ) {
            Text(text = stringResource(id = R.string.send))
        }

    }
}
