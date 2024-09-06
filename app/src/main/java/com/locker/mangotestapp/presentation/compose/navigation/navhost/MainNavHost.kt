package com.locker.mangotestapp.presentation.compose.navigation.navhost

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.locker.mangotestapp.presentation.compose.common.MangoNavHost
import com.locker.mangotestapp.presentation.compose.navigation.screen.Chat
import com.locker.mangotestapp.presentation.compose.navigation.screen.Chats
import com.locker.mangotestapp.presentation.compose.navigation.screen.ChatsList
import com.locker.mangotestapp.presentation.compose.navigation.screen.Profile
import com.locker.mangotestapp.presentation.compose.screens.chat.screen.ChatWithMessagesScreen
import com.locker.mangotestapp.presentation.compose.screens.chat.screen.ChatListScreen
import com.locker.mangotestapp.presentation.compose.screens.profile.screen.ProfileScreen


@Composable
fun MainNavHost(
    innerPadding: PaddingValues,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    MangoNavHost(
        startDestination = Chats,
        navController = navController,
        modifier = modifier.padding(innerPadding)
    ) {
        navigation<Chats>(startDestination = ChatsList) {
            composable<ChatsList> {
                ChatListScreen(
                    onChatClick = { chatId ->
                        navController.navigate(route = Chat(chatId)) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable<Chat> { navBackStackEntry ->
                val chat: Chat = navBackStackEntry.toRoute()

                ChatWithMessagesScreen(
                    chatId = chat.id,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        composable<Profile> {
            ProfileScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
