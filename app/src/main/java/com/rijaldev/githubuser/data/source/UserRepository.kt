package com.rijaldev.githubuser.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rijaldev.githubuser.data.source.local.LocalDataSource
import com.rijaldev.githubuser.data.source.local.entity.DetailRepoEntity
import com.rijaldev.githubuser.data.source.local.entity.DetailUserEntity
import com.rijaldev.githubuser.data.source.local.entity.RepoEntity
import com.rijaldev.githubuser.data.source.local.entity.UserEntity
import com.rijaldev.githubuser.data.source.remote.RemoteDataSource
import com.rijaldev.githubuser.data.source.remote.response.ApiResponse
import com.rijaldev.githubuser.data.source.remote.response.detail.DetailUserResponse
import com.rijaldev.githubuser.data.source.remote.response.detailrepo.DetailRepoResponse
import com.rijaldev.githubuser.data.source.remote.response.repo.RepoResponse
import com.rijaldev.githubuser.data.source.remote.response.search.SearchResponse
import com.rijaldev.githubuser.data.source.remote.response.user.UserResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): UserDataSource {

    override fun getUsers(): MutableLiveData<ApiResponse<List<UserEntity>>> {
        val userResults = MutableLiveData<ApiResponse<List<UserEntity>>>()
        remoteDataSource.getUsers(object : RemoteDataSource.LoadUsersCallback {
            override fun onLoadUsersCallback(userResponse: ApiResponse<List<UserResponse>?>?) {
                if (userResponse != null) {
                    val listUser = ArrayList<UserEntity>()
                    userResponse.body?.forEach {
                        val userEntity = UserEntity(
                            it.avatarUrl,
                            it.userId,
                            it.login,
                            it.type
                        )
                        listUser.add(userEntity)
                    }
                    userResults.postValue(ApiResponse(userResponse.status, listUser, userResponse.message))
                }
            }
        })
        return userResults
    }

    override fun searchUser(query: String): LiveData<ApiResponse<List<UserEntity>>> {
        val searchResult = MutableLiveData<ApiResponse<List<UserEntity>>>()
        remoteDataSource.searchUser(query, object : RemoteDataSource.LoadSearchCallback {
            override fun onLoadSearchCallback(searchResponse: ApiResponse<SearchResponse?>?) {
                if (searchResponse != null) {
                    val listUser = ArrayList<UserEntity>()
                    searchResponse.body?.items?.forEach {
                        val entity = UserEntity(
                            it.avatarUrl,
                            it.userId,
                            it.login,
                            it.type
                        )
                        listUser.add(entity)
                    }
                    searchResult.postValue(ApiResponse(searchResponse.status, listUser, searchResponse.message))
                }
            }
        })
        return searchResult
    }

    override fun getDetailUser(username: String): LiveData<ApiResponse<DetailUserEntity>> {
        val detailResult = MutableLiveData<ApiResponse<DetailUserEntity>>()
        remoteDataSource.getDetailUser(username, object : RemoteDataSource.LoadDetailCallback {
            override fun onLoadDetailCallback(detailUserResponse: ApiResponse<DetailUserResponse?>?) {
                detailUserResponse?.body?.apply {
                    val detailEntity = DetailUserEntity(null,
                        userId,
                        login,
                        name,
                        type,
                        bio,
                        company,
                        location,
                        blog,
                        publicRepos,
                        followers,
                        avatarUrl,
                        following
                    )
                    detailResult.postValue(ApiResponse(detailUserResponse.status, detailEntity, detailUserResponse.message))
                }
            }
        })
        return detailResult
    }

    override fun getFollowers(username: String): LiveData<ApiResponse<List<UserEntity>>> {
        val followerResults = MutableLiveData<ApiResponse<List<UserEntity>>>()
        remoteDataSource.getFollowers(username, object : RemoteDataSource.LoadUsersCallback {
            override fun onLoadUsersCallback(userResponse: ApiResponse<List<UserResponse>?>?) {
                if (userResponse != null) {
                    val listUser = ArrayList<UserEntity>()
                    userResponse.body?.forEach {
                        val userEntity = UserEntity(
                            it.avatarUrl,
                            it.userId,
                            it.login,
                            it.type
                        )
                        listUser.add(userEntity)
                    }
                    followerResults.postValue(ApiResponse(userResponse.status, listUser, userResponse.message))
                }
            }
        })
        return followerResults
    }

    override fun getFollowing(username: String): LiveData<ApiResponse<List<UserEntity>>> {
        val followingResults = MutableLiveData<ApiResponse<List<UserEntity>>>()
        remoteDataSource.getFollowing(username, object : RemoteDataSource.LoadUsersCallback {
            override fun onLoadUsersCallback(userResponse: ApiResponse<List<UserResponse>?>?) {
                if (userResponse != null) {
                    val listUser = ArrayList<UserEntity>()
                    userResponse.body?.forEach {
                        val userEntity = UserEntity(
                            it.avatarUrl,
                            it.userId,
                            it.login,
                            it.type
                        )
                        listUser.add(userEntity)
                    }
                    followingResults.postValue(ApiResponse(userResponse.status, listUser, userResponse.message))
                }
            }
        })
        return followingResults
    }

    override fun getRepos(username: String): LiveData<ApiResponse<List<RepoEntity>>> {
        val repoResults = MutableLiveData<ApiResponse<List<RepoEntity>>>()
        remoteDataSource.getRepos(username, object : RemoteDataSource.LoadReposCallback {
            override fun onLoadReposCallback(repoResponse: ApiResponse<List<RepoResponse>?>?) {
                if (repoResponse != null) {
                    val listRepo = ArrayList<RepoEntity>()
                    repoResponse.body?.forEach {
                        val repoEntity = RepoEntity(
                            it.stargazersCount,
                            it.visibility,
                            it.name,
                            it.description,
                            it.language,
                            it.owner.login,
                            it.id
                        )
                        listRepo.add(repoEntity)
                    }
                    repoResults.postValue(ApiResponse(repoResponse.status, listRepo, repoResponse.message))
                }
            }
        })
        return repoResults
    }

    override fun getDetailRepo(username: String, repository: String
    ): LiveData<ApiResponse<DetailRepoEntity>> {
        val detailResults = MutableLiveData<ApiResponse<DetailRepoEntity>>()
        remoteDataSource.getDetailRepo(username, repository, object
            : RemoteDataSource.LoadDetailRepoCallback {
            override fun onLoadDetailRepoCallback(detailResponse: ApiResponse<DetailRepoResponse?>?) {
                detailResponse?.body?.apply {
                    val entity = DetailRepoEntity(
                        fullName,
                        stargazersCount,
                        openIssuesCount,
                        networkCount,
                        htmlUrl,
                        name,
                        description,
                        language,
                        subscribersCount,
                        id,
                        watchersCount,
                        forksCount
                    )
                    detailResults.postValue(ApiResponse(detailResponse.status, entity, detailResponse.message))
                }
            }
        })
        return detailResults
    }

    override fun getFavoriteUsers(): LiveData<List<DetailUserEntity>> =
        localDataSource.getFavoriteUsers()

    override suspend fun insert(user: DetailUserEntity) =
        localDataSource.insert(user)

    override fun isFavorite(id: Int): Int =
        localDataSource.isFavoriteUser(id)

    override suspend fun delete(id: Int) =
        localDataSource.delete(id)
}