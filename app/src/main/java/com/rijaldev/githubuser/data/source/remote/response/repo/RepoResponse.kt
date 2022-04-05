package com.rijaldev.githubuser.data.source.remote.response.repo

import com.google.gson.annotations.SerializedName
import com.rijaldev.githubuser.data.source.remote.response.user.UserResponse

data class RepoResponse(

	@field:SerializedName("stargazers_count")
	val stargazersCount: Int,

	@field:SerializedName("visibility")
	val visibility: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("language")
	val language: String,

	@field:SerializedName("owner")
	val owner: UserResponse,

	@field:SerializedName("id")
	val id: Int
)
