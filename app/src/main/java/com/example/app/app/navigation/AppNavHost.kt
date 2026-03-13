package com.example.app.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.app.app.AppState
import com.example.app.core.navigation.NavigationEvent
import com.example.app.feature.home.presentation.HomeRoute
import com.example.app.feature.sample.presentation.DetailRoute

@Composable
fun AppNavHost(appState: AppState) {
    LaunchedEffect(appState.navController, appState.navigator) {
        appState.navigator.navigationEvents.collect { event ->
            when (event) {
                is NavigationEvent.NavigateTo -> {
                    appState.navController.navigate(event.route)
                }
                is NavigationEvent.NavigateUp -> appState.navController.navigateUp()
                is NavigationEvent.PopTo -> {
                    appState.navController.popBackStack(event.route, event.inclusive)
                }
            }
        }
    }

    NavHost(
        navController = appState.navController,
        startDestination = Destinations.Home.route,
    ) {
        composable(route = Destinations.Home.route) {
            HomeRoute(
                navigator = appState.navigator,
                viewModel = hiltViewModel(),
            )
        }
        composable(
            route = Destinations.SampleDetail.route,
            arguments = listOf(
                navArgument(NavArguments.ITEM_ID) {
                    type = NavType.LongType
                },
            ),
        ) {
            DetailRoute(
                navigator = appState.navigator,
                viewModel = hiltViewModel(),
            )
        }
    }
}
