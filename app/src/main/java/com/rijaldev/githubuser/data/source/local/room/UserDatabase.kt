package com.rijaldev.githubuser.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rijaldev.githubuser.data.source.local.entity.DetailUserEntity

@Database(entities = [DetailUserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}