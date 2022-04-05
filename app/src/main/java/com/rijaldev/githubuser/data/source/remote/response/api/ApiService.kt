package com.rijaldev.githubuser.data.source.remote.response.api

import com.rijaldev.githubuser.data.source.remote.response.detail.DetailUserResponse
import com.rijaldev.githubuser.data.source.remote.response.detailrepo.DetailRepoResponse
import com.rijaldev.githubuser.data.source.remote.response.repo.RepoResponse
import com.rijaldev.githubuser.data.source.remote.response.search.SearchResponse
import com.rijaldev.githubuser.data.source.remote.response.user.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("users")
    fun getUsers(): Call<List<UserResponse>>

    @Headers("Authorization: token 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")
    @GET("search/users")
    fun searchUser(
        @Query("q") query: String
    ): Call<SearchResponse>

    @Headers("Authorization: token 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")
    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @Headers("Authorization: token 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")
    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<UserResponse>>

    @Headers("Authorization: token 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")
    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<UserResponse>>

    @Headers("Authorization: token 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")
    @GET("users/{username}/repos")
    fun getRepos(
        @Path("username") username: String
    ): Call<List<RepoResponse>>

    @Headers("Authorization: token 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")
    @GET("repos/{username}/{repository}")
    fun getDetailRepo(
        @Path("username") username: String,
        @Path("repository") repository: String
    ): Call<DetailRepoResponse>
}