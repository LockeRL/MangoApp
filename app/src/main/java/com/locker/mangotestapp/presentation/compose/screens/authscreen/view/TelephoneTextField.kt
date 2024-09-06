package com.locker.mangotestapp.presentation.compose.screens.authscreen.view

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import com.hbb20.CountryCodePicker
import com.locker.mangotestapp.R
import com.locker.mangotestapp.presentation.compose.common.MangoTextField
import com.locker.mangotestapp.presentation.compose.theme.Size48
import com.locker.mangotestapp.presentation.model.CountryCode
import com.locker.mangotestapp.presentation.util.deleteNotDigits
import com.locker.mangotestapp.presentation.util.formatAndStrip

@Composable
fun TelephoneTextField(
    number: String,
    onNumberChange: (String) -> Unit,
    onCountryCodeChange: (CountryCode) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    countryRegionCode: String = Locale.current.region
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current
    val countryCodePicker = remember { CountryCodePicker(context) }
    countryCodePicker.setDefaultCountryUsingNameCode(countryRegionCode)

    var selectedCountryNameCode by remember { mutableStateOf(countryCodePicker.selectedCountryNameCode) }

    LaunchedEffect(selectedCountryNameCode) {
        onCountryCodeChange(
            CountryCode(
                countryName = countryCodePicker.selectedCountryNameCode,
                countryCode = countryCodePicker.selectedCountryCodeWithPlus
            )
        )
    }

    MangoTextField(
        value = formatAndStrip(selectedCountryNameCode, number),
        onValueChange = { newString -> onNumberChange(newString.deleteNotDigits()) },
        label = stringResource(id = R.string.phone),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
        ),
        leadingIcon = {
            Row(modifier = Modifier.fillMaxHeight()) {
                CountryCodePickerView(
                    countryCodePicker = countryCodePicker,
                    isClickable = !readOnly
                ) { newCode ->
                    selectedCountryNameCode = newCode
                }
            }
        },
        readOnly = readOnly,
        modifier = modifier.height(IntrinsicSize.Min)
    )
}

@Preview
@Composable
fun TelephoneTextFieldPreview() {
    TelephoneTextField(
        number = "37245987239",
        onNumberChange = { },
        onCountryCodeChange = {},
        modifier = Modifier.fillMaxWidth()
    )
}
