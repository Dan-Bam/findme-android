package com.example.data.repository

import com.example.data.remote.datasource.RemoteLostDataSource
import com.example.data.remote.request.lost.toRequest
import com.example.data.remote.response.lost.toEntity
import com.example.domain.entity.lost.LostEntity
import com.example.domain.param.lost.LostParam
import com.example.domain.repository.LostRepository
import javax.inject.Inject

class LostRepositoryImpl @Inject constructor(
    private val lostDataSource: RemoteLostDataSource
): LostRepository {
    override suspend fun registerLost(lostParam: LostParam) {
        return lostDataSource.registerLost(lostParam.toRequest())
    }

    override suspend fun editLost(lostId: String, lostParam: LostParam) {
        return lostDataSource.editLost(lostId, lostParam.toRequest())
    }

    override suspend fun deleteLost(lostId: String) {
        return lostDataSource.deleteLost(lostId)
    }

    override suspend fun findAll(): List<LostEntity> {
        return lostDataSource.findAll().map { it.toEntity() }
    }
}