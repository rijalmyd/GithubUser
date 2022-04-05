package com.rijaldev.githubuser.data.source.remote.response.search

import com.google.gson.annotations.SerializedName
import com.rijaldev.githubuser.data.source.remote.response.user.UserResponse

data class SearchResponse(

	@field:SerializedName("items")
	val items: List<UserResponse>
)
