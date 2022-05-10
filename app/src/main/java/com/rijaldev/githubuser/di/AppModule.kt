package com.rijaldev.githubuser.di

import com.rijaldev.githubuser.data.repository.user.UserRepository
import com.rijaldev.githubuser.data.repository.user.UserRepositoryImpl
import com.rijaldev.githubuser.data.local.LocalDataSource
import com.rijaldev.githubuser.data.local.datastore.SettingPreference
import com.rijaldev.githubuser.data.local.room.UserDatabase
import com.rijaldev.githubuser.data.remote.RemoteDataSource
import com.rijaldev.githubuser.data.remote.api.ApiService
import com.rijaldev.githubuser.data.repository.theme.ThemeRepository
import com.rijaldev.githubuser.data.repository.theme.ThemeRepositoryImpl
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
    fun provideUserRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): UserRepository =
        UserRepositoryImpl(remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun provideThemeRepository(localDataSource: LocalDataSource): ThemeRepository =
        ThemeRepositoryImpl(localDataSource)

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
        RemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideLocalDataSource(database: UserDatabase, settingPref: SettingPreference): LocalDataSource =
        LocalDataSource(database.userDao(), settingPref)
}