package com.rijaldev.githubuser.data.remote.response.user

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("id")
	val userId: Int,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("type")
	val type: String
)
