package com.rijaldev.githubuser.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijaldev.githubuser.data.UserRepository
import com.rijaldev.githubuser.data.local.entity.DetailUserEntity
import com.rijaldev.githubuser.data.local.entity.UserEntity
import com.rijaldev.githubuser.data.remote.response.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    val dataUser = userRepository.getUsers()

    fun searchUser(query: String): LiveData<Result<List<UserEntity>>> =
        userRepository.searchUser(query)

    fun getFavUsers(): LiveData<List<DetailUserEntity>> = userRepository.getFavoriteUsers()

    fun isDarkModeActive(): LiveData<Boolean> = userRepository.isDarkModeActive()

    fun setThemeMode(isDarkMode: Boolean) = viewModelScope.launch {
        userRepository.setThemeMode(isDarkMode)
    }
}