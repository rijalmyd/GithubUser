package com.rijaldev.githubuser.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.rijaldev.githubuser.BuildConfig

object Constant {
    const val TOKEN = BuildConfig.TOKEN
    const val BASE_URL = BuildConfig.BASE_URL
    const val SETTING_PREFERENCE = "setting_preference"
    val THEME_KEY = booleanPreferencesKey("theme_key")
}