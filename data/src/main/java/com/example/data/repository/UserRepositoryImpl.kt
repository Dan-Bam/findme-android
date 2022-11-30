package com.example.data.repository

import com.example.data.remote.datasource.RemoteUserDataSource
import com.example.data.remote.request.user.toRequest
import com.example.data.remote.response.user.toEntity
import com.example.domain.entity.user.InfoEntity
import com.example.domain.entity.user.MyFoundEntity
import com.example.domain.entity.user.MyLostEntity
import com.example.domain.param.user.EditInfoParam
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: RemoteUserDataSource
): UserRepository {
    override suspend fun myLost(): List<MyLostEntity> {
        return userDataSource.myLost().map { it.toEntity() }
    }

    override suspend fun myFound(): List<MyFoundEntity> {
        return userDataSource.myFound().map { it.toEntity() }
    }

    override suspend fun myInfo(): InfoEntity {
        return userDataSource.myInfo().toEntity()
    }

    override suspend fun editInfo(editInfoParam: EditInfoParam) {
        return userDataSource.editInfo(editInfoParam.toRequest())
    }

    override suspend fun recommendFound(): List<MyFoundEntity> {
        return userDataSource.recommendFound().map { it.toEntity() }
    }

    override suspend fun logout() {
        return userDataSource.logout()
    }
}