package com.rijaldev.githubuser.data.repository.theme

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData

interface ThemeRepository {
    fun isDarkModeActive(): LiveData<Boolean>

    suspend fun setThemeMode(isDarkMode: Boolean): Preferences
}