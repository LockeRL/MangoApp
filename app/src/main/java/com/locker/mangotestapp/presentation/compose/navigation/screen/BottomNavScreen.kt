package com.locker.mangotestapp.presentation.compose.navigation.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.locker.mangotestapp.R
import kotlinx.serialization.Serializable

@Serializable
object Chats
@Serializable
object Profile

@Serializable
object ChatsList
@Serializable
data class Chat(val id: String)

data class BottomNavRoute<T: Any>(val name: String, val route: T, @DrawableRes val iconId: Int, @StringRes val caption: Int)

val BOTTOM_NAV_SCREENS = listOf(
    BottomNavRoute("ChatsScreen", Chats, R.drawable.ic_chats, R.string.chats),
    BottomNavRoute("ProfileScreen", Profile, R.drawable.ic_user_profile, R.string.user_profile)
)
