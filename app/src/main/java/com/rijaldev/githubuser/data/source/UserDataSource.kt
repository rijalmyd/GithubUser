package com.rijaldev.githubuser.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rijaldev.githubuser.data.source.local.entity.DetailRepoEntity
import com.rijaldev.githubuser.data.source.local.entity.DetailUserEntity
import com.rijaldev.githubuser.data.source.local.entity.RepoEntity
import com.rijaldev.githubuser.data.source.local.entity.UserEntity
import com.rijaldev.githubuser.data.source.remote.response.ApiResponse

interface UserDataSource {
    fun getUsers(): MutableLiveData<ApiResponse<List<UserEntity>>>

    fun searchUser(query: String): LiveData<ApiResponse<List<UserEntity>>>

    fun getDetailUser(username: String): LiveData<ApiResponse<DetailUserEntity>>

    fun getFollowers(username: String): LiveData<ApiResponse<List<UserEntity>>>

    fun getFollowing(username: String): LiveData<ApiResponse<List<UserEntity>>>

    fun getRepos(username: String): LiveData<ApiResponse<List<RepoEntity>>>

    fun getDetailRepo(username: String, repository: String): LiveData<ApiResponse<DetailRepoEntity>>

    fun getFavoriteUsers(): LiveData<List<DetailUserEntity>>

    suspend fun insert(user: DetailUserEntity)

    fun isFavorite(id: Int): Int

    suspend fun delete(id: Int)
}