package com.locker.mangotestapp.presentation.compose.navigation.navhost

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.locker.mangotestapp.presentation.compose.common.MangoNavHost
import com.locker.mangotestapp.presentation.compose.common.MangoSnackBar
import com.locker.mangotestapp.presentation.compose.navigation.screen.AuthScreen
import com.locker.mangotestapp.presentation.compose.screens.authscreen.screen.LogInScreen
import com.locker.mangotestapp.presentation.compose.screens.authscreen.screen.RegisterScreen
import com.locker.mangotestapp.presentation.compose.screens.authscreen.state.LoginUIState
import com.locker.mangotestapp.presentation.compose.screens.authscreen.viewmodel.AuthViewModel
import com.locker.mangotestapp.presentation.compose.theme.Space16
import com.locker.mangotestapp.presentation.util.collectAsEffect
import com.locker.mangotestapp.presentation.util.showSnackBar
import org.koin.compose.koinInject

@Composable
fun AuthNavHost(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = koinInject(),
) {
    val navController = rememberNavController()
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    val resources = LocalContext.current.resources

    authViewModel.errorId.collectAsEffect { errorId ->
        snackScope.showSnackBar(snackState, resources.getString(errorId))
    }

    authViewModel.authState.collectAsEffect { state ->
        if (state is LoginUIState.Redirect)
            navController.navigate(AuthScreen.RegisterScreen.name) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
    }

    Box(modifier = modifier) {
        MangoNavHost(
            startDestination = AuthScreen.LogInScreen.name,
            navController = navController,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(AuthScreen.LogInScreen.name) {
                LogInScreen(
                    authViewModel = authViewModel,
                    modifier = Modifier
                        .padding(Space16)
                        .fillMaxSize()
                )
            }

            composable(AuthScreen.RegisterScreen.name) {
                RegisterScreen(
                    authViewModel = authViewModel,
                    modifier = Modifier
                        .padding(Space16)
                        .fillMaxSize()
                )
            }
        }

        MangoSnackBar(
            snackBarState = snackState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(Space16)
        )
    }
}
