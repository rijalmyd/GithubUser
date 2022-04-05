package com.rijaldev.githubuser.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rijaldev.githubuser.data.source.local.entity.DetailUserEntity

@Dao
interface UserDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insert(user: DetailUserEntity)

    @Query("SELECT COUNT(*) FROM detail_user_entity WHERE userId = :id")
    fun isFavorite(id: Int): Int

    @Query("SELECT * FROM detail_user_entity ORDER BY id DESC")
    fun getFavoriteUsers(): LiveData<List<DetailUserEntity>>

    @Query("DELETE FROM detail_user_entity WHERE userId = :id")
    suspend fun delete(id: Int)
}