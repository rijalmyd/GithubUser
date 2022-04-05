package com.rijaldev.githubuser.data.source.local

import androidx.lifecycle.LiveData
import com.rijaldev.githubuser.data.source.local.entity.DetailUserEntity
import com.rijaldev.githubuser.data.source.local.room.UserDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val userDao: UserDao) {

    fun getFavoriteUsers(): LiveData<List<DetailUserEntity>> = userDao.getFavoriteUsers()

    suspend fun insert(user: DetailUserEntity) = userDao.insert(user)

    fun isFavoriteUser(id: Int) = userDao.isFavorite(id)

    suspend fun delete(id: Int) = userDao.delete(id)
}