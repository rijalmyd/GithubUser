package com.rijaldev.githubuser.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingPreference @Inject constructor(private val dataStore: DataStore<Preferences>) {
    private val themeKey = booleanPreferencesKey("theme_key")

    fun isDarkModeActive(): Flow<Boolean> =
        dataStore.data.map {
            it[themeKey] ?: false
        }

    suspend fun setThemeMode(isDarkMode: Boolean) =
        dataStore.edit {
            it[themeKey] = isDarkMode
        }
}