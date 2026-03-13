package com.example.app.app.navigation

import com.example.app.core.navigation.Route

sealed class Destinations(override val route: String) : Route {
    data object Home : Destinations("home")
    data object SampleDetail : Destinations("sample/{${NavArguments.ITEM_ID}}") {
        fun createRoute(itemId: Long): String = "sample/$itemId"
    }
}
