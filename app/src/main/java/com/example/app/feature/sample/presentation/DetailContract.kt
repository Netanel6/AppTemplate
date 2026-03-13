package com.example.app.feature.sample.presentation

import com.example.app.core.architecture.mvi.UiAction
import com.example.app.core.architecture.mvi.UiEvent
import com.example.app.core.architecture.mvi.UiState
import com.example.app.feature.sample.presentation.model.DetailUiModel

object DetailContract {
    sealed interface Action : UiAction {
        data object Load : Action
        data object Retry : Action
        data object BackClicked : Action
    }

    data class State(
        val isLoading: Boolean = true,
        val detail: DetailUiModel? = null,
        val errorMessage: String? = null,
    ) : UiState

    sealed interface Event : UiEvent {
        data object NavigateBack : Event
    }
}
