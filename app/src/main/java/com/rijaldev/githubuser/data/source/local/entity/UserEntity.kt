package com.rijaldev.githubuser.data.source.local.entity

data class UserEntity(
    var avatarUrl: String?,
    var userId: Int?,
    val login: String?,
    val type: String?
)