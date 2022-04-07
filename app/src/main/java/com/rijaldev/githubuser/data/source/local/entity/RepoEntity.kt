package com.rijaldev.githubuser.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepoEntity(
    val stargazersCount: Int?,
    val visibility: String?,
    val name: String?,
    val description: String?,
    val language: String?,
    val owner: String?,
    val id: Int
): Parcelable