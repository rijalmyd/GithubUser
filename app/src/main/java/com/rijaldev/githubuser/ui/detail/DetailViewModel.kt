package com.rijaldev.githubuser.ui.detail

import androidx.lifecycle.*
import com.rijaldev.githubuser.data.source.UserRepository
import com.rijaldev.githubuser.data.source.local.entity.DetailUserEntity
import com.rijaldev.githubuser.data.source.local.entity.RepoEntity
import com.rijaldev.githubuser.data.source.local.entity.UserEntity
import com.rijaldev.githubuser.data.source.remote.response.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    private val username = MutableLiveData<String>()

    fun setUsername(username: String) {
        this.username.value = username
    }

    var detailUser: LiveData<ApiResponse<DetailUserEntity>> = Transformations.switchMap(username) {
        userRepository.getDetailUser(it)
    }

    var getFollowers: LiveData<ApiResponse<List<UserEntity>>> = Transformations.switchMap(username) {
        userRepository.getFollowers(it)
    }

    var getFollowing: LiveData<ApiResponse<List<UserEntity>>> = Transformations.switchMap(username) {
        userRepository.getFollowing(it)
    }

    val getRepository: LiveData<ApiResponse<List<RepoEntity>>> = Transformations.switchMap(username) {
        userRepository.getRepos(it)
    }

    fun insert() = viewModelScope.launch {
        val user = detailUser.value
        if (user != null) {
            val resource = user.body
            if (resource != null) {
                userRepository.insert(resource)
            }
        }
    }

    fun delete() = viewModelScope.launch {
        val user = detailUser.value
        if (user != null) {
            val resource = user.body?.userId
            if (resource != null) {
                userRepository.delete(resource)
            }
        }
    }

    fun isFavorite(id: Int) : Int {
        return userRepository.isFavorite(id)
    }
}