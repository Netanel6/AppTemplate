package com.example.app.core.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigatorImpl : Navigator {
    private val mutableNavigationEvents = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    override val navigationEvents: SharedFlow<NavigationEvent> = mutableNavigationEvents.asSharedFlow()

    override suspend fun navigate(route: String) {
        mutableNavigationEvents.emit(NavigationEvent.NavigateTo(route))
    }

    override suspend fun navigateUp() {
        mutableNavigationEvents.emit(NavigationEvent.NavigateUp)
    }

    override suspend fun popTo(route: String, inclusive: Boolean) {
        mutableNavigationEvents.emit(NavigationEvent.PopTo(route, inclusive))
    }
}
