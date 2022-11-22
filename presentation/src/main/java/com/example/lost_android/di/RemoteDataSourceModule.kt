package com.example.lost_android.di

import com.example.data.remote.datasource.RemoteAuthDataSource
import com.example.data.remote.datasource.RemoteAuthDataSourceImpl
import com.example.data.remote.datasource.RemoteLostDataSource
import com.example.data.remote.datasource.RemoteLostDataSourceImpl
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
}