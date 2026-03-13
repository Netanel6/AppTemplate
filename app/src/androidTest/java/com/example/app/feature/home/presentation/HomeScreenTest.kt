package com.example.app.feature.home.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun showsEmptyState() {
        composeRule.setContent {
            HomeScreen(
                state = HomeContract.State(),
                onAction = {},
                snackbarHostState = SnackbarHostState(),
            )
        }

        composeRule.onNodeWithText("Nothing here yet").assertIsDisplayed()
    }
}
