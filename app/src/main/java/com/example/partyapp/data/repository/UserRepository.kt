package com.example.partyapp.data.repository

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.partyapp.data.dao.UserDAO
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.partyapp.data.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserRepository(private val userDAO: UserDAO, private val context: Context) {

    val users: Flow<List<User>> = userDAO.getUsers()

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "logged_user_preferences")
        private val SESSION_INFO = stringPreferencesKey(name="session_info")
    }
    val preferenceFlow: Flow<String> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[SESSION_INFO]?: ""
        }
    suspend fun saveToDataStore(user: User) {
        context.dataStore.edit { preferences ->
            preferences[SESSION_INFO] = user.username
        }
    }
    suspend fun clearDataStore(){
        context.dataStore.edit {
                preferences -> preferences[SESSION_INFO] = ""
        }
    }

    @WorkerThread
    fun checkLoginCredentials(username: String, password:String): User? {
        return userDAO.checkLoginCredentials(username, password)
    }
    @WorkerThread
    fun getUserFromUsername(username: String): Flow<User> {
        return userDAO.getUserFromUsername(username)
    }
    @WorkerThread
    fun getUserFromId(id: Int): Flow<User> {
        return userDAO.getUserFromId(id)
    }
    @WorkerThread
    suspend fun changeUsernameFromId(userId: Int, newUsername: String){
        userDAO.changeUsernameFromId(userId, newUsername)
    }
    @WorkerThread
    suspend fun changePfpFromId(userId: Int, newPfp: String){
        userDAO.changePfpFromId(userId, newPfp)
    }
    @WorkerThread
    suspend fun updateExpFromId(userId: Int, newExp: String){
        userDAO.updateExpFromId(userId, newExp)
    }
    @WorkerThread
    suspend fun insertNewUser(user: User){
        userDAO.insert(user)
    }

}