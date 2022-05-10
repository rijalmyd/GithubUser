package com.rijaldev.githubuser.ui.detailrepo

import androidx.lifecycle.*
import com.rijaldev.githubuser.data.repository.user.UserRepository
import com.rijaldev.githubuser.data.local.entity.DetailRepoEntity
import com.rijaldev.githubuser.data.remote.Result
import com.rijaldev.githubuser.utils.DoubleTrigger
import com.rijaldev.githubuser.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailRepoViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    private val username = MutableLiveData<String>()
    private val repositoryName = MutableLiveData<String>()
    private val _isResultHasBeenHandled = MutableLiveData<Event<Boolean>>()
    val isResultHasBeenHandled: LiveData<Event<Boolean>> get() = _isResultHasBeenHandled

    fun setData(username: String, repositoryName: String) {
        this.username.value = username
        this.repositoryName.value = repositoryName
    }

    val getDetailRepository: LiveData<Result<DetailRepoEntity>> = Transformations.switchMap(DoubleTrigger(username, repositoryName)) {
        userRepository.getDetailRepo(it.first.toString(), it.second.toString())
    }

    fun setHasBeenHandled() {
        _isResultHasBeenHandled.value = Event(true)
    }
}