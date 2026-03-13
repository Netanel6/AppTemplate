package com.example.app.core.common.extension

import androidx.navigation.NavController

fun NavController.navigateSingleTop(route: String) {
    navigate(route) {
        launchSingleTop = true
    }
}
