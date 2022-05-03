package com.rijaldev.githubuser.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rijaldev.githubuser.data.local.entity.DetailUserEntity
import com.rijaldev.githubuser.data.local.entity.UserEntity

@Database(entities = [DetailUserEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}