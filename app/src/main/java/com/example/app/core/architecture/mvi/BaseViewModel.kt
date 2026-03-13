package com.example.app.core.architecture.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : UiState, A : UiAction, E : UiEvent>(
    initialState: S,
) : ViewModel() {
    private val mutableState = MutableStateFlow(initialState)
    private val mutableEvent = MutableSharedFlow<E>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    val state: StateFlow<S> = mutableState.asStateFlow()
    val events: SharedFlow<E> = mutableEvent.asSharedFlow()

    abstract fun onAction(action: A)

    protected fun setState(reducer: Reducer<S>) {
        mutableState.update(reducer::reduce)
    }

    protected fun setState(transform: S.() -> S) {
        mutableState.update(transform)
    }

    protected fun sendEvent(event: E) {
        viewModelScope.launch {
            mutableEvent.emit(event)
        }
    }
}
