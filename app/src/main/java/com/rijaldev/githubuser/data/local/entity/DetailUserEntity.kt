package com.rijaldev.githubuser.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "detail_user_entity")
@Parcelize
data class DetailUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val userId: Int,
    val login: String?,
    val name: String?,
    val type: String?,
    val bio: String?,
    val company: String?,
    val location: String?,
    val blog: String?,
    val publicRepos: Int?,
    val followers: Int?,
    val avatarUrl: String?,
    val following: Int?
): Parcelable