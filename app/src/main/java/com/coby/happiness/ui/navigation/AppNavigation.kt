package com.coby.happiness.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.coby.cobylibrary.ui.element.basic.CBDivider
import com.coby.cobylibrary.ui.theme.BackgroundNormalNormal
import com.coby.cobylibrary.ui.theme.LabelAlternative
import com.coby.cobylibrary.ui.theme.RedNormal
import com.coby.cobylibrary.ui.theme.Typography
import com.coby.happiness.R
import com.coby.happiness.ui.bunch.BunchScreen
import com.coby.happiness.ui.map.MapScreen
import com.coby.happiness.ui.profile.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val items = listOf(Screen.Home, Screen.Map, Screen.Bunch, Screen.Profile)

    Scaffold(
        bottomBar = {
            CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                NavigationBar(
                    containerColor = Color.BackgroundNormalNormal(),
                    contentColor = Color.MainColor()
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.icon),
                                    contentDescription = screen.route
                                )
                            },
                            label = {
                                Text(
                                    text = screen.label,
                                    style = Typography.labelSmall,
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.MainColor(),
                                selectedTextColor = Color.MainColor(),
                                unselectedIconColor = Color.LabelAlternative(),
                                unselectedTextColor = Color.LabelAlternative(),
                                indicatorColor = Color.Transparent
                            ),
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }

                CBDivider()
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Home.route,
            Modifier
                .background(Color.BackgroundNormalNormal())
                .padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeNavigation() }
            composable(Screen.Map.route) { MapScreen() }
            composable(Screen.Bunch.route) { BunchScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}

sealed class Screen(val route: String, val label: String, val icon: Int) {
    data object Home : Screen("home", "홈", R.drawable.ic_home)
    data object Map : Screen("map", "지도", R.drawable.ic_map)
    data object Bunch : Screen("bunch", "뭉치", R.drawable.ic_travel)
    data object Profile : Screen("profile", "정보", R.drawable.ic_person)
}

private object NoRippleTheme: RippleTheme {
    @Composable
    override fun defaultColor() = Color.Transparent

    @Composable
    override fun rippleAlpha() = RippleAlpha(0F, 0F, 0F, 0F)
}

@Composable
fun Color.Companion.MainColor(): Color {
    return Color.RedNormal()
}