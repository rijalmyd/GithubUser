package com.rijaldev.githubuser.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rijaldev.githubuser.data.source.UserRepository
import com.rijaldev.githubuser.data.source.local.entity.DetailRepoEntity
import com.rijaldev.githubuser.data.source.remote.response.ApiResponse
import com.rijaldev.githubuser.utils.DoubleTrigger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailRepoViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    private val username = MutableLiveData<String>()
    private val repositoryName = MutableLiveData<String>()

    fun setData(username: String, repositoryName: String) {
        this.username.value = username
        this.repositoryName.value = repositoryName
    }

    val getDetailRepository: LiveData<ApiResponse<DetailRepoEntity>> = Transformations.switchMap(DoubleTrigger(username, repositoryName)) {
        userRepository.getDetailRepo(it.first.toString(), it.second.toString())
    }
}