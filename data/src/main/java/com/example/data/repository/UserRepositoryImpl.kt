package com.example.data.repository

import com.example.data.remote.datasource.RemoteUserDataSource
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: RemoteUserDataSource
): UserRepository {
}