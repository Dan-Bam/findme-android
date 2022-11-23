package com.example.data.remote.datasource

import com.example.data.remote.network.LostAPI
import com.example.data.remote.request.lost.LostRequest
import com.example.data.remote.response.lost.LostResponse
import com.example.data.util.HttpHandler
import javax.inject.Inject

class RemoteLostDataSourceImpl @Inject constructor(
    private val lostAPI: LostAPI
): RemoteLostDataSource {
    override suspend fun registerLost(lostRequest: LostRequest) {
        return HttpHandler<Unit>()
            .httpRequest { lostAPI.registerLost(lostRequest) }
            .sendRequest()
    }

    override suspend fun findAll(): List<LostResponse> {
        return HttpHandler<List<LostResponse>>()
            .httpRequest { lostAPI.findAll() }
            .sendRequest()
    }
}