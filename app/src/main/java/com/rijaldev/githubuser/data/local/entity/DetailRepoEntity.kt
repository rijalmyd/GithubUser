package com.rijaldev.githubuser.data.local.entity

data class DetailRepoEntity(
    val fullName: String?,
    val stargazersCount: Int?,
    val openIssuesCount: Int?,
    val networkCount: Int?,
    val htmlUrl: String?,
    val name: String?,
    val description: String?,
    val language: String?,
    val subscribersCount: Int?,
    val id: Int?,
    val watchersCount: Int?,
    val forksCount: Int?
)