package com.example.data.remote.datasource

import com.example.data.remote.network.LostAPI
import com.example.data.remote.request.lost.EditLostRequest
import com.example.data.remote.request.lost.LostRequest
import com.example.data.remote.response.lost.LostResponse
import com.example.data.util.HttpHandler
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class RemoteLostDataSourceImpl @Inject constructor(
    private val lostAPI: LostAPI
): RemoteLostDataSource {
    override suspend fun registerLost(lostRequest: LostRequest, file: MultipartBody.Part) {
        return HttpHandler<Unit>()
            .httpRequest { lostAPI.registerLost(lostRequest, file) }
            .sendRequest()
    }

    override suspend fun editLost(
        lostId: String,
        editLostRequest: EditLostRequest,
        file: MultipartBody.Part?
    ) {
        return HttpHandler<Unit>()
            .httpRequest { lostAPI.editLost(lostId, editLostRequest, file) }
            .sendRequest()
    }

    override suspend fun deleteLost(lostId: String) {
        return HttpHandler<Unit>()
            .httpRequest { lostAPI.deleteLost(lostId) }
            .sendRequest()
    }

    override suspend fun detailLost(lostId: String): LostResponse {
        return HttpHandler<LostResponse>()
            .httpRequest { lostAPI.detailLost(lostId) }
            .sendRequest()
    }

    override suspend fun findAll(): List<LostResponse> {
        return HttpHandler<List<LostResponse>>()
            .httpRequest { lostAPI.findAll() }
            .sendRequest()
    }

    override suspend fun findCategory(category: String?, address: String): List<LostResponse> {
        return HttpHandler<List<LostResponse>>()
            .httpRequest { lostAPI.findCategory(category, address) }
            .sendRequest()
    }
}