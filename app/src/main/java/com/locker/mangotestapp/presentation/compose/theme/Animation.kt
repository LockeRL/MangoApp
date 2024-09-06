package com.locker.mangotestapp.presentation.compose.theme

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry

val RightEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition
    @Composable
    get() = {
        fadeIn(animationSpec = tween(ANIM_DURATION)) + slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Right, tween(ANIM_DURATION)
        )
    }

val LeftExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
    @Composable
    get() = {
        fadeOut(animationSpec = tween(ANIM_DURATION)) + slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Left, tween(ANIM_DURATION)
        )
    }
