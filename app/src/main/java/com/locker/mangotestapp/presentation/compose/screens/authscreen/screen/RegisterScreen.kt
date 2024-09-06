package com.locker.mangotestapp.presentation.compose.screens.authscreen.screen

import android.Manifest
import android.content.Context
import android.telephony.TelephonyManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.locker.mangotestapp.R
import com.locker.mangotestapp.presentation.compose.common.MangoButton
import com.locker.mangotestapp.presentation.compose.common.MangoTextField
import com.locker.mangotestapp.presentation.compose.screens.authscreen.view.TelephoneTextField
import com.locker.mangotestapp.presentation.compose.screens.authscreen.viewmodel.AuthViewModel
import com.locker.mangotestapp.presentation.compose.theme.Size48
import com.locker.mangotestapp.presentation.compose.theme.Space16
import com.locker.mangotestapp.presentation.compose.theme.Space8
import com.locker.mangotestapp.presentation.util.getRegionFromNumber
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    phone: String,
    register: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }

    val nameFocusRequester = FocusRequester()
    val usernameFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TelephoneTextField(
            number = phone,
            countryRegionCode = getRegionFromNumber(phone) ?: Locale.current.region,
            onNumberChange = {},
            onCountryCodeChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Space8)
        )

        MangoTextField(
            value = name,
            onValueChange = { newName -> name = newName },
            label = stringResource(id = R.string.name),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { usernameFocusRequester.requestFocus() }),
            modifier = Modifier
                .focusRequester(nameFocusRequester)
                .fillMaxWidth()
                .padding(bottom = Space8)
        )

        MangoTextField(
            value = username,
            onValueChange = { newUsername -> username = newUsername },
            label = stringResource(id = R.string.username),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            modifier = Modifier
                .focusRequester(usernameFocusRequester)
                .fillMaxWidth()
                .padding(bottom = Space8)
        )

        MangoButton(
            text = stringResource(id = R.string.register),
            onClick = {
                focusManager.clearFocus()
                keyboardController?.hide()
                register(name, username)
            },
            modifier = Modifier
                .height(Size48)
                .fillMaxWidth()
        )
    }
}

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = koinViewModel()
) {
    RegisterScreen(
        phone = authViewModel.phone,
        register = authViewModel::register,
        modifier = modifier
    )
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        phone = "79999999999",
        register = { _, _ -> },
        modifier = Modifier
            .padding(Space16)
            .fillMaxSize()
    )
}
