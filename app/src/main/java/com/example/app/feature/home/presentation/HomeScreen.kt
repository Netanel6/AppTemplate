package com.example.app.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.app.R
import com.example.app.app.navigation.Destinations
import com.example.app.core.designsystem.component.AppEmptyState
import com.example.app.core.designsystem.component.AppErrorView
import com.example.app.core.designsystem.component.AppLoading
import com.example.app.core.designsystem.component.AppScaffold
import com.example.app.core.designsystem.component.AppTopBar
import com.example.app.core.designsystem.component.PrimaryButton
import com.example.app.core.navigation.Navigator
import com.example.app.feature.home.presentation.component.HomeListItem
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun HomeRoute(
    navigator: Navigator,
    viewModel: HomeViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel, navigator) {
        viewModel.events.collect { event ->
            when (event) {
                is HomeContract.Event.NavigateToDetail -> {
                    navigator.navigate(Destinations.SampleDetail.createRoute(event.id))
                }
                is HomeContract.Event.ShowMessage -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    HomeScreen(
        state = state,
        onAction = viewModel::onAction,
        snackbarHostState = snackbarHostState,
    )
}

@Composable
fun HomeScreen(
    state: HomeContract.State,
    onAction: (HomeContract.Action) -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    AppScaffold(
        topBar = {
            AppTopBar(title = stringResource(R.string.home_title))
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            SnackbarHost(hostState = snackbarHostState)
            when {
                state.isLoading -> AppLoading()
                state.errorMessage != null -> AppErrorView(
                    message = state.errorMessage,
                    onRetry = { onAction(HomeContract.Action.Retry) },
                )
                state.isEmpty -> AppEmptyState(
                    title = stringResource(R.string.empty_title),
                    message = stringResource(R.string.empty_message),
                )
                else -> HomeContent(
                    state = state,
                    onAction = onAction,
                )
            }
        }
    }
}

@Composable
private fun HomeContent(
    state: HomeContract.State,
    onAction: (HomeContract.Action) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            PrimaryButton(
                text = stringResource(R.string.refresh),
                onClick = { onAction(HomeContract.Action.Refresh) },
            )
        }
        items(
            items = state.items,
            key = { it.id },
        ) { item ->
            HomeListItem(
                item = item,
                onClick = { id -> onAction(HomeContract.Action.ItemClicked(id)) },
            )
        }
    }
}
