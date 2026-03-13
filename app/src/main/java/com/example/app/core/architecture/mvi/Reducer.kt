package com.example.app.core.architecture.mvi

fun interface Reducer<S : UiState> {
    fun reduce(currentState: S): S
}
