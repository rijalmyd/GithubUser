package com.rijaldev.githubuser.data.remote.response.detailrepo

import com.google.gson.annotations.SerializedName

data class DetailRepoResponse(

	@field:SerializedName("full_name")
	val fullName: String,

	@field:SerializedName("stargazers_count")
	val stargazersCount: Int,

	@field:SerializedName("open_issues_count")
	val openIssuesCount: Int,

	@field:SerializedName("network_count")
	val networkCount: Int,

	@field:SerializedName("html_url")
	val htmlUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("language")
	val language: String,

	@field:SerializedName("subscribers_count")
	val subscribersCount: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("watchers_count")
	val watchersCount: Int,

	@field:SerializedName("forks_count")
	val forksCount: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("topics")
	val topics: List<String>?
)
