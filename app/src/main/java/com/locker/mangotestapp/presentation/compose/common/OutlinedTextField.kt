package com.locker.mangotestapp.presentation.compose.common

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MangoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false
) {
    val colors = OutlinedTextFieldDefaults.colors()
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        enabled = !readOnly,
        colors = colors.copy(
            disabledIndicatorColor = if (readOnly) colors.unfocusedIndicatorColor else colors.focusedIndicatorColor
        ),
        maxLines = 1,
        readOnly = readOnly,
        modifier = modifier
    )
}
