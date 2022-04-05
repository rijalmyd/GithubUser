package com.rijaldev.githubuser.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rijaldev.githubuser.data.source.UserRepository
import com.rijaldev.githubuser.data.source.local.entity.DetailUserEntity
import com.rijaldev.githubuser.data.source.local.entity.UserEntity
import com.rijaldev.githubuser.data.source.remote.response.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    var dataUsers: MutableLiveData<ApiResponse<List<UserEntity>>> = userRepository.getUsers()

    fun searchUser(query: String): LiveData<ApiResponse<List<UserEntity>>> =
        userRepository.searchUser(query)

    fun getFavUsers(): LiveData<List<DetailUserEntity>> = userRepository.getFavoriteUsers()

}