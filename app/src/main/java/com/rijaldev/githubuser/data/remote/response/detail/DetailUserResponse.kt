package com.rijaldev.githubuser.data.remote.response.detail

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("bio")
	val bio: String,

	@field:SerializedName("company")
	val company: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("id")
	val userId: Int,

	@field:SerializedName("public_repos")
	val publicRepos: Int,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("blog")
	val blog: String,
)
