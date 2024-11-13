package com.example.partyapp.data.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.partyapp.data.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO{
    @Transaction
    @Query("SELECT * FROM user")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE username=:username AND password=:password")
    fun checkLoginCredentials(username: String,password:String) : User?

    @Query("SELECT * FROM user WHERE username=:username")
    fun getUserFromUsername(username: String) : User

    @Query("UPDATE user SET username=:newUsername WHERE id=:userId")
    suspend fun changeUsernameFromId(userId: Int, newUsername: String)

    @Query("UPDATE user SET pfp=:newPfp WHERE id=:userId")
    suspend fun changePfpFromId(userId: Int, newPfp: String)

    @Query("UPDATE user SET exp=:newExp WHERE id=:userId")
    suspend fun updateExpFromId(userId: Int, newExp: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg user: User)
}