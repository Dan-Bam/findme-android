package com.example.data.remote.datasource

import com.example.data.remote.network.LostAPI
import com.example.data.remote.response.lost.FindAllResponse
import com.example.data.util.HttpHandler
import javax.inject.Inject

class RemoteLostDataSourceImpl @Inject constructor(
    private val lostAPI: LostAPI
): RemoteLostDataSource {
    override suspend fun findAll(): List<FindAllResponse> {
        return HttpHandler<List<FindAllResponse>>()
            .httpRequest { lostAPI.findAll() }
            .sendRequest()
    }
}