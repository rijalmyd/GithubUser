package com.rijaldev.githubuser.ui.detail

import androidx.lifecycle.*
import com.rijaldev.githubuser.data.UserRepository
import com.rijaldev.githubuser.data.local.entity.DetailUserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val username = MutableLiveData<String>()

    fun setUsername(username: String) {
        this.username.value = username
    }

    val detailUser = Transformations.switchMap(username) {
        userRepository.getDetailUser(it)
    }

    val getFollowers = Transformations.switchMap(username) {
        userRepository.getFollowers(it)
    }

    val getFollowing = Transformations.switchMap(username) {
        userRepository.getFollowing(it)
    }

    val getRepository = Transformations.switchMap(username) {
        userRepository.getRepos(it)
    }

    fun addToFavorite(user: DetailUserEntity) = viewModelScope.launch {
        userRepository.addToFavorite(user)
    }

    fun removeFromFavorite(id: Int) = viewModelScope.launch {
        userRepository.removeFromFavorite(id)
    }

    fun isFavorite(id: Int): Int = userRepository.isFavorite(id)
}