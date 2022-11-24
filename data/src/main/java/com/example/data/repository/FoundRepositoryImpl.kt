package com.example.data.repository

import com.example.data.remote.datasource.RemoteFoundDataSource
import com.example.data.remote.request.found.toRequest
import com.example.domain.param.param.FoundParam
import com.example.domain.repository.FoundRepository
import javax.inject.Inject

class FoundRepositoryImpl @Inject constructor(
    private val foundDataSource: RemoteFoundDataSource
): FoundRepository {
    override suspend fun registerFound(foundParam: FoundParam) {
        return foundDataSource.registerFound(foundParam.toRequest())
    }
}