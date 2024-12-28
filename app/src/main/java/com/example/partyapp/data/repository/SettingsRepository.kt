package com.example.partyapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.partyapp.data.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.IOException

/**
 * Repository that stores the user's preferences for the ui theme
 */
class SettingsRepository(private val context: Context) {

    //object declaration inside a class
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings_preferences")

        private val UI_THEME = stringPreferencesKey("ui_theme")
    }

    val preferenceFlow: Flow<UserSettings?> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences -> preferences[UI_THEME]?:"" }
        .map { json -> if (json != "") Json.decodeFromString(json) else null }

    suspend fun saveToDataStore(settings: UserSettings) {
        context.dataStore.edit { preferences ->
            preferences[UI_THEME] = Json.encodeToString(settings)
        }
    }
}

