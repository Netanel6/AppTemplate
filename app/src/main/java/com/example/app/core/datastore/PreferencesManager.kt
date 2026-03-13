package com.example.app.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.app.core.datastore.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    val preferences: Flow<UserPreferences> = dataStore.data.map { preferences ->
        UserPreferences(
            useDarkTheme = preferences[Keys.DarkMode] ?: false,
            onboardingCompleted = preferences[Keys.OnboardingCompleted] ?: false,
        )
    }

    suspend fun setDarkMode(enabled: Boolean) {
        dataStore.edit { it[Keys.DarkMode] = enabled }
    }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        dataStore.edit { it[Keys.OnboardingCompleted] = completed }
    }

    private object Keys {
        val DarkMode = booleanPreferencesKey("dark_mode")
        val OnboardingCompleted = booleanPreferencesKey("onboarding_completed")
    }
}
