package com.example.beginnertrainingandroid2025

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
object Home // ホーム画面

@Serializable
object Bookmark // ブックマーク画面

data class TopLevelRoute<T : Any>(val route: T, val icon: ImageVector)

val topLevelRoutes = listOf(
    TopLevelRoute(Home, Icons.Outlined.Home),
    TopLevelRoute(Bookmark, Icons.Outlined.FavoriteBorder)
)

@Composable
fun BeginnerTrainingApp(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BeginnerTrainingNavigationBar(
                modifier = modifier,
                navController = navController
            )
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Home,
        ) {

            composable<Home> {
                HomeScreen()
            }
            composable<Bookmark> {
                BookmarkScreen()
            }
        }
    }
}

@Composable
fun BeginnerTrainingNavigationBar(
    navController: NavHostController,
    modifier: Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(modifier = modifier) {
        topLevelRoutes.forEach { route ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.hasRoute(route.route::class) } == true,
                onClick = {
                    navController.navigate(route.route)
                },
                icon = {
                    Icon(
                        imageVector = route.icon,
                        contentDescription = null,
                    )
                },
            )
        }
    }
}