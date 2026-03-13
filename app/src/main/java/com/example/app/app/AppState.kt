package com.example.app.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.app.core.navigation.Navigator
import com.example.app.core.navigation.NavigatorImpl

data class AppState(
    val navController: NavHostController,
    val navigator: Navigator,
)

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
): AppState = remember(navController) {
    AppState(
        navController = navController,
        navigator = NavigatorImpl(),
    )
}
