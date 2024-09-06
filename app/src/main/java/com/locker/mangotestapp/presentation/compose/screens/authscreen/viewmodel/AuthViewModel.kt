package com.locker.mangotestapp.presentation.compose.screens.authscreen.viewmodel

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locker.mangotestapp.R
import com.locker.mangotestapp.domain.model.CheckAuthCode
import com.locker.mangotestapp.domain.model.RegisterUser
import com.locker.mangotestapp.domain.model.SendAuthCode
import com.locker.mangotestapp.domain.repository.IAuthRepository
import com.locker.mangotestapp.presentation.compose.screens.authscreen.state.LoginUIState
import com.locker.mangotestapp.presentation.model.CountryCode
import com.locker.mangotestapp.presentation.util.maxPhoneLength
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: IAuthRepository
) : ViewModel() {
    var phone by mutableStateOf("")
        private set

    private var countryCode by mutableStateOf(DEFAULT_COUNTRY_CODE)

    private val fullPhone
        get() = "${countryCode.countryCode}$phone"

    private var maxPhoneLength by mutableIntStateOf(0)

    private val _errorId: MutableSharedFlow<Int> = MutableSharedFlow()
    val errorId: SharedFlow<Int> = _errorId

    private val _authState = MutableStateFlow<LoginUIState>(LoginUIState.Idle)
    val authState: StateFlow<LoginUIState> = _authState

    private var _isPhoneSuccessful: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isPhoneSuccessful: StateFlow<Boolean> = _isPhoneSuccessful

    fun setPhoneNumber(number: String) {
        if (number.length > maxPhoneLength)
            return

        phone = number
    }

    fun setPhoneCountryCode(code: CountryCode) {
        countryCode = code
        maxPhoneLength = maxPhoneLength(code.countryName)
    }

    fun waitForCode() {
        viewModelScope.launch {
            val isSuccess = authRepository.sendAuthCode(
                SendAuthCode(fullPhone)
            )

            if (!isSuccess) {
                setErrorId(R.string.error_number)
                _isPhoneSuccessful.value = false
                return@launch
            } else
                _isPhoneSuccessful.value = true
        }
    }

    fun logIn(code: String) {
        viewModelScope.launch {
            val loginSuccess = authRepository.checkAuthCode(
                CheckAuthCode(phone = fullPhone, code = "133337")
            )

            if (loginSuccess)
                _authState.value = LoginUIState.Success
            else
                _authState.value = LoginUIState.Redirect
        }
    }

    fun register(name: String, username: String) {
        viewModelScope.launch {
            val isSuccess = authRepository.registerUser(
                RegisterUser(fullPhone, name, username)
            )

            if (isSuccess)
                _authState.value = LoginUIState.Success
            else {
                setErrorId(R.string.register_error)
                _authState.value = LoginUIState.Idle
            }
        }
    }

    private fun setErrorId(@StringRes strId: Int) {
        viewModelScope.launch {
            _errorId.emit(strId)
        }
    }

    private companion object {
        val DEFAULT_COUNTRY_CODE = CountryCode("", "")
    }
}
