package com.example.app.feature.sample.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.app.R
import com.example.app.core.designsystem.component.AppErrorView
import com.example.app.core.designsystem.component.AppLoading
import com.example.app.core.designsystem.component.AppScaffold
import com.example.app.core.designsystem.component.AppTopBar
import com.example.app.core.navigation.Navigator

@Composable
fun DetailRoute(
    navigator: Navigator,
    viewModel: DetailViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(viewModel, navigator) {
        viewModel.events.collect { event ->
            when (event) {
                DetailContract.Event.NavigateBack -> navigator.navigateUp()
            }
        }
    }

    DetailScreen(
        state = state,
        onAction = viewModel::onAction,
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    state: DetailContract.State,
    onAction: (DetailContract.Action) -> Unit,
) {
    AppScaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.sample_detail_title),
                canNavigateBack = true,
                onNavigateBack = { onAction(DetailContract.Action.BackClicked) },
            )
        },
    ) { paddingValues ->
        when {
            state.isLoading -> AppLoading(modifier = Modifier.padding(paddingValues))
            state.errorMessage != null -> AppErrorView(
                message = state.errorMessage,
                onRetry = { onAction(DetailContract.Action.Retry) },
                modifier = Modifier.padding(paddingValues),
            )
            state.detail != null -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                GlideImage(
                    model = state.detail.imageUrl,
                    contentDescription = state.detail.title,
                )
                Text(text = state.detail.title, style = MaterialTheme.typography.headlineMedium)
                Text(text = state.detail.description, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
