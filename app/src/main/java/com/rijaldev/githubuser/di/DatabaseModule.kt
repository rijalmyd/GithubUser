package com.rijaldev.githubuser.di

import android.content.Context
import androidx.room.Room
import com.rijaldev.githubuser.data.local.room.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            "user.db"
        ).build()
}