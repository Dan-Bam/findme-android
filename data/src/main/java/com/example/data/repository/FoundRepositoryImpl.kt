package com.example.data.repository

import com.example.data.remote.datasource.RemoteFoundDataSource
import com.example.data.remote.request.found.toRequest
import com.example.domain.param.found.EditFoundParam
import com.example.domain.param.found.FoundParam
import com.example.domain.repository.FoundRepository
import javax.inject.Inject

class FoundRepositoryImpl @Inject constructor(
    private val foundDataSource: RemoteFoundDataSource
): FoundRepository {
    override suspend fun registerFound(foundParam: FoundParam) {
        return foundDataSource.registerFound(foundParam.toRequest())
    }

    override suspend fun editFound(foundId: String, editFoundParam: EditFoundParam) {
        return foundDataSource.editFound(foundId, editFoundParam.toRequest())
    }
}