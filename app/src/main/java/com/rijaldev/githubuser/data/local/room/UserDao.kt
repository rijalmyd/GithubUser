package com.rijaldev.githubuser.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rijaldev.githubuser.data.local.entity.DetailUserEntity
import com.rijaldev.githubuser.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUsers(users: List<UserEntity>)

    @Query("DELETE FROM user_entity")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_entity")
    fun getUsers(): LiveData<List<UserEntity>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(user: DetailUserEntity)

    @Query("SELECT COUNT(*) FROM detail_user_entity WHERE userId = :id")
    fun isFavorite(id: Int): Int

    @Query("SELECT * FROM detail_user_entity ORDER BY id DESC")
    fun getFavoriteUsers(): LiveData<List<DetailUserEntity>>

    @Query("DELETE FROM detail_user_entity WHERE userId = :id")
    suspend fun removeFromFavorite(id: Int)
}