package com.artemissoftware.metisquotes.presentation.composables.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.artemissoftware.metisquotes.presentation.navigation.Route

@Composable
fun MQBottomNavigationBar(
    navController: NavHostController,
) {
    val items = listOf(
        Route.DailyQuotes, Route.RandomQuote
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    when (item) {
                        Route.DailyQuotes -> Icon(
                            Icons.Default.Home,
                            contentDescription = null,
                            tint = Color.White
                        )

                        Route.RandomQuote -> Icon(
                            Icons.Default.FormatQuote,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                label = { Text(stringResource(id = item.description ), color = Color.White) },
                selected = currentDestination?.hierarchy?.any {
                    it.route == item.route
                } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}