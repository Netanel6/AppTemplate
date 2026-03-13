package com.example.app.core.navigation

sealed interface NavigationEvent {
    data class NavigateTo(val route: String) : NavigationEvent
    data class PopTo(val route: String, val inclusive: Boolean) : NavigationEvent
    data object NavigateUp : NavigationEvent
}
