package com.rijaldev.githubuser.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_entity")
@Parcelize
data class UserEntity(
    @PrimaryKey
    var userId: Int?,
    var avatarUrl: String?,
    val login: String?,
    val type: String?
): Parcelable