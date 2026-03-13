package com.example.app.core.navigation

import kotlinx.coroutines.flow.SharedFlow

interface Navigator {
    val navigationEvents: SharedFlow<NavigationEvent>

    suspend fun navigate(route: String)
    suspend fun navigateUp()
    suspend fun popTo(route: String, inclusive: Boolean = false)
}
