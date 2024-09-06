package com.locker.mangotestapp.presentation.compose.screens.authscreen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.hbb20.CountryCodePicker
import com.locker.mangotestapp.R
import com.locker.mangotestapp.presentation.compose.theme.Size36
import com.locker.mangotestapp.presentation.compose.theme.Space4
import com.locker.mangotestapp.presentation.compose.theme.Typography

@Composable
fun CountryCodePickerView(
    countryCodePicker: CountryCodePicker,
    modifier: Modifier = Modifier,
    isClickable: Boolean = true,
    onCountryCodeChange: (String) -> Unit
) {
    countryCodePicker.setOnCountryChangeListener {
        onCountryCodeChange(countryCodePicker.selectedCountryNameCode)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxHeight()
            .clickable {
                if (isClickable)
                    countryCodePicker.launchCountrySelectionDialog()
            }
    ) {
        Image(
            painter = painterResource(id = countryCodePicker.selectedCountryFlagResourceId),
            contentDescription = stringResource(id = R.string.img_flag),
            modifier = Modifier
                .padding(Space4)
                .width(Size36)
        )

        Text(
            text = countryCodePicker.selectedCountryCodeWithPlus,
            style = Typography.bodyMedium,
            modifier = Modifier.width(Size36)
        )
    }
}
