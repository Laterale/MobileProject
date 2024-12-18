package com.example.partyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyapp.data.UserSettings
import com.example.partyapp.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor (
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    val settings = settingsRepository.preferenceFlow

    fun saveSettings(settings: UserSettings) {
        viewModelScope.launch {
            settingsRepository.saveToDataStore(settings)
        }
    }
}