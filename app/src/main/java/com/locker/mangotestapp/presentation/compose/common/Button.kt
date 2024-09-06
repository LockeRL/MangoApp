package com.locker.mangotestapp.presentation.compose.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.locker.mangotestapp.presentation.compose.theme.DefaultShape
import com.locker.mangotestapp.presentation.compose.theme.Typography

@Composable
fun MangoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    borderStroke: BorderStroke? = null,
    containerColor: Color = Color.Black,
    contentColor: Color = Color.White,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding
) {
    Button(
        onClick = onClick,
        shape = DefaultShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        contentPadding = contentPadding,
        border = borderStroke,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(
            text = text.uppercase(),
            style = Typography.titleMedium
        )
    }
}
