package com.example.lost_android.di

import com.example.data.local.datasource.LocalAuthDataStore
import com.example.data.local.datasource.LocalAuthDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun provideAuthDataSource(
        authDataSourceImpl: LocalAuthDataStoreImpl
    ): LocalAuthDataStore
}