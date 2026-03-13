package com.example.app.feature.home.presentation

import com.example.app.core.architecture.mvi.UiAction
import com.example.app.core.architecture.mvi.UiEvent
import com.example.app.core.architecture.mvi.UiState
import com.example.app.feature.home.presentation.model.HomeItemUiModel

object HomeContract {
    sealed interface Action : UiAction {
        data object Load : Action
        data object Refresh : Action
        data object Retry : Action
        data class ItemClicked(val id: Long) : Action
    }

    data class State(
        val isLoading: Boolean = false,
        val items: List<HomeItemUiModel> = emptyList(),
        val errorMessage: String? = null,
        val isOffline: Boolean = false,
    ) : UiState {
        val isEmpty: Boolean = !isLoading && errorMessage == null && items.isEmpty()
    }

    sealed interface Event : UiEvent {
        data class NavigateToDetail(val id: Long) : Event
        data class ShowMessage(val message: String) : Event
    }
}
