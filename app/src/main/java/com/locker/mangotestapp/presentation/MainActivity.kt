package com.locker.mangotestapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.locker.mangotestapp.presentation.compose.navigation.BottomNavBar
import com.locker.mangotestapp.presentation.compose.navigation.navhost.MainNavHost
import com.locker.mangotestapp.presentation.compose.theme.MangoTestAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MangoTestAppTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                Scaffold(
                    bottomBar = {
                        BottomNavBar(
                            currentDestination = navBackStackEntry?.destination,
                            navigateScreen = { screenRoute ->
                                navController.navigate(route = screenRoute) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    MainNavHost(
                        innerPadding = innerPadding,
                        navController = navController,
                        modifier = Modifier.fillMaxSize()
                    )
                }

            }
        }
    }
}
