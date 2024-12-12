package com.example.partyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyapp.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor (
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    val theme = settingsRepository.themePreferenceFlow
    val useSysDefault = settingsRepository.sysDefaultPreferenceFlow

    fun saveTheme(theme: String) {
        viewModelScope.launch {
            settingsRepository.saveThemeToDataStore(theme)
        }
    }

    fun saveUseSysDefaultTheme(useSysDefault: String) {
        viewModelScope.launch {
            settingsRepository.saveUseSysThemeToDataStore(useSysDefault)
        }
    }
}