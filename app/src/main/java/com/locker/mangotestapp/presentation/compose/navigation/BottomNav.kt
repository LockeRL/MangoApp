package com.locker.mangotestapp.presentation.compose.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.util.fastForEach
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.locker.mangotestapp.presentation.compose.navigation.screen.BOTTOM_NAV_SCREENS
import com.locker.mangotestapp.presentation.compose.theme.DividerSize
import com.locker.mangotestapp.presentation.compose.theme.Size48
import com.locker.mangotestapp.presentation.compose.theme.Typography

@Composable
fun BottomNavBar(
    currentDestination: NavDestination?,
    navigateScreen: (Any) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        HorizontalDivider(thickness = DividerSize, color = Color.Gray)
        BottomAppBar(
            modifier = modifier.height(Size48)
        ) {
            BOTTOM_NAV_SCREENS.fastForEach { screen ->
                val isSelected = currentDestination?.hierarchy?.any { destination ->
                    destination.hasRoute(screen.route::class)
                } == true

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { navigateScreen(screen.route) }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = screen.iconId),
                        contentDescription = null,
                        tint = if (isSelected) Color.Red else Color.White,
                        modifier = Modifier.weight(1f)
                    )

                    Text(text = stringResource(id = screen.caption), style = Typography.bodySmall)
                }
            }
        }
    }
}
