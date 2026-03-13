package com.example.app.feature.sample.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.app.app.navigation.NavArguments
import com.example.app.core.architecture.mvi.BaseViewModel
import com.example.app.core.architecture.result.AppResult
import com.example.app.feature.sample.domain.usecase.GetSampleDetailUseCase
import com.example.app.feature.sample.presentation.model.DetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSampleDetailUseCase: GetSampleDetailUseCase,
) : BaseViewModel<DetailContract.State, DetailContract.Action, DetailContract.Event>(
    initialState = DetailContract.State(),
) {
    private val itemId: Long = checkNotNull(savedStateHandle[NavArguments.ITEM_ID])

    init {
        onAction(DetailContract.Action.Load)
    }

    override fun onAction(action: DetailContract.Action) {
        when (action) {
            DetailContract.Action.Load,
            DetailContract.Action.Retry -> load()

            DetailContract.Action.BackClicked -> sendEvent(DetailContract.Event.NavigateBack)
        }
    }

    private fun load() {
        viewModelScope.launch {
            setState { copy(isLoading = true, errorMessage = null) }
            when (val result = getSampleDetailUseCase(itemId)) {
                is AppResult.Success -> {
                    setState {
                        copy(
                            isLoading = false,
                            detail = DetailUiModel(
                                id = result.data.id,
                                title = result.data.title,
                                description = result.data.description,
                                imageUrl = result.data.imageUrl,
                            ),
                        )
                    }
                }
                is AppResult.Error -> {
                    setState {
                        copy(
                            isLoading = false,
                            errorMessage = result.message ?: "Unable to load detail.",
                        )
                    }
                }
            }
        }
    }
}
