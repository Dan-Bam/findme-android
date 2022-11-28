package com.example.data.repository

import com.example.data.remote.datasource.RemoteLostDataSource
import com.example.data.remote.request.lost.toRequest
import com.example.data.remote.response.lost.toEntity
import com.example.domain.entity.lost.LostEntity
import com.example.domain.param.lost.EditLostParam
import com.example.domain.param.lost.LostParam
import com.example.domain.repository.LostRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class LostRepositoryImpl @Inject constructor(
    private val lostDataSource: RemoteLostDataSource
): LostRepository {
    override suspend fun registerLost(lostParam: LostParam, file: MultipartBody.Part) {
        return lostDataSource.registerLost(lostParam.toRequest(), file)
    }

    override suspend fun editLost(
        lostId: String,
        editLostParam: EditLostParam,
        file: MultipartBody.Part?
    ) {
        return lostDataSource.editLost(lostId, editLostParam.toRequest(), file)
    }

    override suspend fun deleteLost(lostId: String) {
        return lostDataSource.deleteLost(lostId)
    }

    override suspend fun detailLost(lostId: String): LostEntity {
        return lostDataSource.detailLost(lostId).toEntity()
    }

    override suspend fun findAll(): List<LostEntity> {
        return lostDataSource.findAll().map { it.toEntity() }
    }

    override suspend fun findCategory(category: String?, address: String): List<LostEntity> {
        return lostDataSource.findCategory(category, address).map { it.toEntity() }
    }
}