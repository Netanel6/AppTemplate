package com.example.app.app.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.app.app.MainActivity
import org.junit.Rule
import org.junit.Test

class AppNavHostTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun startsAtHomeDestination() {
        composeRule.onNodeWithText("Starter Home").assertIsDisplayed()
    }
}
