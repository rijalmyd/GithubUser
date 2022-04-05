package com.rijaldev.githubuser.di

import com.rijaldev.githubuser.data.source.UserRepository
import com.rijaldev.githubuser.data.source.local.LocalDataSource
import com.rijaldev.githubuser.data.source.local.room.UserDatabase
import com.rijaldev.githubuser.data.source.remote.RemoteDataSource
import com.rijaldev.githubuser.data.source.remote.response.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): UserRepository =
        UserRepository(remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
        RemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideLocalDataSource(database: UserDatabase): LocalDataSource =
        LocalDataSource(database.userDao())
}