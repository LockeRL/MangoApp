package com.locker.mangotestapp.presentation.compose.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.locker.mangotestapp.presentation.compose.theme.DefaultShape
import com.locker.mangotestapp.presentation.compose.theme.Size48

@Composable
fun MangoSnackBar(
    snackBarState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(
        hostState = snackBarState,
        modifier = modifier.heightIn(Size48)
    ) {
        Snackbar(
            shape = DefaultShape,
            containerColor = Color.Black,
            contentColor = Color.White,
            content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it.visuals.message
                )
            }
        )
    }
}
