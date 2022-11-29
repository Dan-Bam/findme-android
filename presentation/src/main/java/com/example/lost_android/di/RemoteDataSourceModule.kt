package com.example.lost_android.di

import com.example.data.remote.datasource.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun provideRemoteAuthDataSource(
        remoteAuthDataSourceImpl: RemoteAuthDataSourceImpl
    ): RemoteAuthDataSource

    @Binds
    abstract fun provideRemoteLostDataSource(
        remoteLostDataSourceImpl: RemoteLostDataSourceImpl
    ): RemoteLostDataSource

    @Binds
    abstract fun provideRemoteFoundDataSource(
        remoteFoundDataSourceImpl: RemoteFoundDataSourceImpl
    ): RemoteFoundDataSource

    @Binds
    abstract fun provideRemoteUserDataSource(
        remoteUserDataSourceImpl: RemoteUserDataSourceImpl
    ): RemoteUserDataSource

    @Binds
    abstract fun provideRemoteChatDataSource(
        remoteChatDataSourceImpl: RemoteChatDataSourceImpl
    ): RemoteChatDataSource
}