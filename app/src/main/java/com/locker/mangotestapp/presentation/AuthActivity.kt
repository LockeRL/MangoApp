package com.locker.mangotestapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.locker.mangotestapp.presentation.compose.navigation.navhost.AuthNavHost
import com.locker.mangotestapp.presentation.compose.screens.authscreen.state.LoginUIState
import com.locker.mangotestapp.presentation.compose.screens.authscreen.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authViewModel.authState.onEach { state ->
            if (state is LoginUIState.Success) {
                startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                finish()
            }
        }.launchIn(lifecycleScope)

        setContent {
            AuthNavHost(
                authViewModel = authViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
