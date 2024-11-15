package com.example.partyapp.viewModel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyapp.data.entity.User
import com.example.partyapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    val session = repository.preferenceFlow
    fun startSession(user: User) {
        viewModelScope.launch {
            repository.saveToDataStore(user)
        }
    }
    fun clearSession() {
        viewModelScope.launch {
            repository.clearDataStore()
            _loggedUser = null
        }
    }
    private var _loggedUser: User? = null
    val loggedUser
        get() = _loggedUser
    fun selectUser(user: User) {
        _loggedUser = user
    }

    val users = repository.users

    fun getUserFromUsername(username:String) = viewModelScope.launch {
        repository.getUserFromUsername(username)
    }

    fun checkLoginCredentials(username: String, password:String) = repository.checkLoginCredentials(username, password)

    fun changeUsernameFromId(userId: Int, newUsername: String) = viewModelScope.launch {
        repository.changeUsernameFromId(userId, newUsername)
    }

    fun changePfpFromId(userId: Int, newPfp: String) = viewModelScope.launch {
        repository.changePfpFromId(userId, newPfp)
    }

    fun updateExpFromId(userId: Int, newExp: String) = viewModelScope.launch {
        repository.updateExpFromId(userId, newExp)
    }

    fun insertNewUser(user: User) = viewModelScope.launch {
        repository.insertNewUser(user)
    }
}

