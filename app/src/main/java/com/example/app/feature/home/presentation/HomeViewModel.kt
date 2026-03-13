package com.example.app.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.example.app.core.architecture.mvi.BaseViewModel
import com.example.app.core.architecture.result.AppResult
import com.example.app.core.architecture.result.ErrorType
import com.example.app.feature.home.domain.usecase.GetHomeItemsUseCase
import com.example.app.feature.home.domain.usecase.ObserveHomeItemsUseCase
import com.example.app.feature.home.presentation.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeItemsUseCase: GetHomeItemsUseCase,
    observeHomeItemsUseCase: ObserveHomeItemsUseCase,
) : BaseViewModel<HomeContract.State, HomeContract.Action, HomeContract.Event>(
    initialState = HomeContract.State(isLoading = true),
) {
    init {
        observeHomeItemsUseCase()
            .onEach { items ->
                setState {
                    copy(
                        isLoading = false,
                        items = items.map { it.toUiModel() },
                        errorMessage = null,
                    )
                }
            }
            .launchIn(viewModelScope)

        onAction(HomeContract.Action.Load)
    }

    override fun onAction(action: HomeContract.Action) {
        when (action) {
            HomeContract.Action.Load,
            HomeContract.Action.Refresh,
            HomeContract.Action.Retry -> refresh()

            is HomeContract.Action.ItemClicked -> {
                sendEvent(HomeContract.Event.NavigateToDetail(action.id))
            }
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            setState { copy(isLoading = true, errorMessage = null) }
            when (val result = getHomeItemsUseCase()) {
                is AppResult.Success -> setState { copy(isLoading = false) }
                is AppResult.Error -> {
                    setState {
                        copy(
                            isLoading = false,
                            errorMessage = result.message ?: "Unable to refresh content.",
                            isOffline = result.type == ErrorType.NO_CONNECTION,
                        )
                    }
                    sendEvent(HomeContract.Event.ShowMessage(result.message ?: "Request failed."))
                }
            }
        }
    }
}
