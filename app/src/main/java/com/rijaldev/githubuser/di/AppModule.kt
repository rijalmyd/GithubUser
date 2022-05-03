package com.rijaldev.githubuser.di

import com.rijaldev.githubuser.data.UserRepository
import com.rijaldev.githubuser.data.local.LocalDataSource
import com.rijaldev.githubuser.data.local.datastore.SettingPreference
import com.rijaldev.githubuser.data.local.room.UserDatabase
import com.rijaldev.githubuser.data.remote.RemoteDataSource
import com.rijaldev.githubuser.data.remote.response.api.ApiService
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
    fun provideLocalDataSource(database: UserDatabase, settingPref: SettingPreference): LocalDataSource =
        LocalDataSource(database.userDao(), settingPref)
}