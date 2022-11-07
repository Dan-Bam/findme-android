package com.example.lost_android.di

import com.example.data.remote.datasource.AuthDataSource
import com.example.data.remote.datasource.AuthDataSourceImpl
import com.example.data.remote.network.AuthAPI
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun provideAuthDataSource(
        authDataSourceImpl: AuthDataSourceImpl
    ): AuthDataSource
}