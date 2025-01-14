package com.example.partyapp.viewModel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyapp.data.entity.User
import com.example.partyapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun getUserFromUsername(username:String) = repository.getUserFromUsername(username)

    fun checkLoginCredentials(username: String, password:String) = repository.checkLoginCredentials(username, password)

    fun changePfpFromId(userId: Int, newPfp: String) = viewModelScope.launch {
        repository.changePfpFromId(userId, newPfp)
    }

    fun addExpToUser(user: User, newExp: Int) = viewModelScope.launch {
        repository.updateExpFromId(user.id, (user.exp + newExp).toString())
    }

    fun addExpToLoggedUser(exp: Int) = viewModelScope.launch {
        _loggedUser?.let {
            repository.updateExpFromId(it.id, (it.exp + exp).toString())
            it.exp += exp
        }
    }

    suspend fun insertNewUser(user: User) = repository.insertNewUser(user)
    //viewModelScope.launch {
    //    repository.insertNewUser(user)
    //}
}

