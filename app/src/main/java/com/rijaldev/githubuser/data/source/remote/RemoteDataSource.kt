package com.rijaldev.githubuser.data.source.remote

import com.rijaldev.githubuser.data.source.remote.response.ApiResponse
import com.rijaldev.githubuser.data.source.remote.response.api.ApiService
import com.rijaldev.githubuser.data.source.remote.response.detail.DetailUserResponse
import com.rijaldev.githubuser.data.source.remote.response.detailrepo.DetailRepoResponse
import com.rijaldev.githubuser.data.source.remote.response.repo.RepoResponse
import com.rijaldev.githubuser.data.source.remote.response.search.SearchResponse
import com.rijaldev.githubuser.data.source.remote.response.user.UserResponse
import com.rijaldev.githubuser.utils.ErrorHandlingDummy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getUsers(callback: LoadUsersCallback) {
        val client = apiService.getUsers()
        client.enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                if (response.isSuccessful) {
                    callback.onLoadUsersCallback(ApiResponse.success(response.body()))
                } else {
                    callback.onLoadUsersCallback(ApiResponse.empty(response.message(), response.body()))
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                callback.onLoadUsersCallback(ApiResponse.error(t.message.toString(), mutableListOf()))
            }
        })
    }

    fun searchUser(query: String, callback: LoadSearchCallback) {
        val client = apiService.searchUser(query)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onLoadSearchCallback(ApiResponse.success(response.body()))
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                callback.onLoadSearchCallback(ApiResponse.error(t.message.toString(),
                    SearchResponse(mutableListOf())
                ))
            }
        })
    }

    fun getDetailUser(username: String, callback: LoadDetailCallback) {
        val client = apiService.getDetailUser(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onLoadDetailCallback(ApiResponse.success(response.body()))
                } else {
                    callback.onLoadDetailCallback(ApiResponse.empty(response.message(), response.body()))
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                callback.onLoadDetailCallback(ApiResponse.error(t.message.toString(), ErrorHandlingDummy.getDetailDummy()))
            }
        })
    }

    fun getFollowers(username: String, callback: LoadUsersCallback) {
        val client = apiService.getFollowers(username)
        client.enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                if (response.isSuccessful) {
                    callback.onLoadUsersCallback(ApiResponse.success(response.body()))
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                callback.onLoadUsersCallback(ApiResponse.error(t.message.toString(), mutableListOf()))
            }
        })
    }

    fun getFollowing(username: String, callback: LoadUsersCallback) {
        val client = apiService.getFollowing(username)
        client.enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                if (response.isSuccessful) {
                    callback.onLoadUsersCallback(ApiResponse.success(response.body()))
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                callback.onLoadUsersCallback(ApiResponse.error(t.message.toString(), mutableListOf()))
            }
        })
    }

    fun getRepos(username: String, callback: LoadReposCallback) {
        val client = apiService.getRepos(username)
        client.enqueue(object : Callback<List<RepoResponse>> {
            override fun onResponse(
                call: Call<List<RepoResponse>>,
                response: Response<List<RepoResponse>>
            ) {
                if (response.isSuccessful) {
                    callback.onLoadReposCallback(ApiResponse.success(response.body()))
                }
            }

            override fun onFailure(call: Call<List<RepoResponse>>, t: Throwable) {
                callback.onLoadReposCallback(ApiResponse.error(t.message.toString(), mutableListOf()))
            }
        })
    }

    fun getDetailRepo(username: String, repository: String, callback: LoadDetailRepoCallback) {
        val client = apiService.getDetailRepo(username, repository)
        client.enqueue(object : Callback<DetailRepoResponse> {
            override fun onResponse(
                call: Call<DetailRepoResponse>,
                response: Response<DetailRepoResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onLoadDetailRepoCallback(ApiResponse.success(response.body()))
                }
            }

            override fun onFailure(call: Call<DetailRepoResponse>, t: Throwable) {
                callback.onLoadDetailRepoCallback(ApiResponse.error(t.message.toString(), ErrorHandlingDummy.getDetailRepoDummy()))
            }
        })
    }

    interface LoadUsersCallback {
        fun onLoadUsersCallback(userResponse: ApiResponse<List<UserResponse>?>?)
    }

    interface LoadSearchCallback {
        fun onLoadSearchCallback(searchResponse: ApiResponse<SearchResponse?>?)
    }

    interface LoadDetailCallback {
        fun onLoadDetailCallback(detailUserResponse: ApiResponse<DetailUserResponse?>?)
    }

    interface LoadReposCallback {
        fun onLoadReposCallback(repoResponse: ApiResponse<List<RepoResponse>?>?)
    }

    interface LoadDetailRepoCallback {
        fun onLoadDetailRepoCallback(detailResponse: ApiResponse<DetailRepoResponse?>?)
    }
}