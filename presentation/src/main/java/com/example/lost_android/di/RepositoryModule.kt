package com.example.lost_android.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.LostRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.LostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
    @Binds
    abstract fun provideLostRepository(
        lostRepositoryImpl: LostRepositoryImpl
    ): LostRepository
}