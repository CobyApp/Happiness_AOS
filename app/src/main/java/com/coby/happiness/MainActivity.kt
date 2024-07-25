package com.coby.happiness

import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.coby.cobylibrary.ui.theme.BackgroundNormalNormal
import com.coby.cobylibrary.ui.theme.RedNormal
import com.coby.happiness.ui.bunch.BunchScreen
import com.coby.happiness.ui.home.HomeScreen
import com.coby.happiness.ui.map.MapScreen
import com.coby.happiness.ui.profile.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                TRANSPARENT, TRANSPARENT
            )
        )
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val items = listOf(Screen.Home, Screen.Map, Screen.Bunch, Screen.Profile)

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.BackgroundNormalNormal(),
                contentColor = Color.RedNormal()
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.route) },
                        label = { Text(screen.label) },
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
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Home.route,
            Modifier
                .background(Color.BackgroundNormalNormal())
                .padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Map.route) { MapScreen() }
            composable(Screen.Bunch.route) { BunchScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "홈", Icons.Filled.Home)
    object Map : Screen("map", "지도", Icons.Filled.Favorite)
    object Bunch : Screen("bunch", "뭉치", Icons.Filled.Person)
    object Profile : Screen("profile", "정보", Icons.Filled.Person)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}