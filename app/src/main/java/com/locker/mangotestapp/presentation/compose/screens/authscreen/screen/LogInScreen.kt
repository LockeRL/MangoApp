package com.locker.mangotestapp.presentation.compose.screens.authscreen.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.locker.mangotestapp.R
import com.locker.mangotestapp.presentation.compose.common.MangoButton
import com.locker.mangotestapp.presentation.compose.common.MangoTextField
import com.locker.mangotestapp.presentation.compose.screens.authscreen.view.TelephoneTextField
import com.locker.mangotestapp.presentation.compose.screens.authscreen.viewmodel.AuthViewModel
import com.locker.mangotestapp.presentation.compose.theme.Size48
import com.locker.mangotestapp.presentation.compose.theme.Space8
import com.locker.mangotestapp.presentation.model.CountryCode
import com.locker.mangotestapp.presentation.util.matchesUserCode
import org.koin.androidx.compose.koinViewModel

@Composable
fun LogInScreen(
    number: String,
    updateNationalNumber: (String) -> Unit,
    updateCountryCode: (CountryCode) -> Unit,
    logIn: (String) -> Unit,
    isWaitingForCode: Boolean,
    waitForCode: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var code by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TelephoneTextField(
            number = number,
            onNumberChange = updateNationalNumber,
            onCountryCodeChange = updateCountryCode,
            modifier = Modifier
                .padding(bottom = Space8)
                .fillMaxWidth()
        )

        AnimatedVisibility(
            visible = isWaitingForCode
        ) {
            MangoTextField(
                value = code,
                onValueChange = { newString ->
                    if (newString.matchesUserCode())
                        code = newString
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                label = stringResource(id = R.string.code),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Space8)
            )
        }

        MangoButton(
            text = stringResource(id = R.string.log_in),
            onClick = {
                focusManager.clearFocus()
                keyboardController?.hide()

                if (code.isEmpty())
                    waitForCode()
                else
                    logIn(code)

                code = ""
            },
            modifier = Modifier
                .height(Size48)
                .fillMaxWidth()
        )
    }
}

@Composable
fun LogInScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = koinViewModel()
) {
    val isWaitingForCode by authViewModel.isPhoneSuccessful.collectAsState()

    LogInScreen(
        number = authViewModel.phone,
        logIn = authViewModel::logIn,
        isWaitingForCode = isWaitingForCode,
        waitForCode = authViewModel::waitForCode,
        updateNationalNumber = authViewModel::setPhoneNumber,
        updateCountryCode = authViewModel::setPhoneCountryCode,
        modifier = modifier
    )
}

@Preview
@Composable
fun LogInScreenPreview() {
    LogInScreen(
        number = "23742384",
        isWaitingForCode = true,
        logIn = {},
        waitForCode = {},
        updateNationalNumber = {},
        updateCountryCode = {},
        modifier = Modifier.fillMaxSize()
    )
}
