package com.example.data.remote.datasource

import com.example.data.remote.network.UserAPI
import com.example.data.remote.request.user.EditInfoRequest
import com.example.data.remote.response.user.InfoResponse
import com.example.data.remote.response.user.MyFoundResponse
import com.example.data.remote.response.user.MyLostResponse
import com.example.data.util.HttpHandler
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val userAPI: UserAPI
): RemoteUserDataSource {
    override suspend fun myLost(): List<MyLostResponse> {
        return HttpHandler<List<MyLostResponse>>()
            .httpRequest { userAPI.myLost() }
            .sendRequest()
    }

    override suspend fun myFound(): List<MyFoundResponse> {
        return HttpHandler<List<MyFoundResponse>>()
            .httpRequest { userAPI.myFound() }
            .sendRequest()
    }

    override suspend fun myInfo(): InfoResponse {
        return HttpHandler<InfoResponse>()
            .httpRequest { userAPI.myInfo() }
            .sendRequest()
    }

    override suspend fun editInfo(editInfoRequest: EditInfoRequest) {
        return HttpHandler<Unit>()
            .httpRequest { userAPI.editInfo(editInfoRequest) }
            .sendRequest()
    }

    override suspend fun recommendFound(): List<MyFoundResponse> {
        return HttpHandler<List<MyFoundResponse>>()
            .httpRequest { userAPI.recommendFound() }
            .sendRequest()
    }

    override suspend fun logout() {
        return HttpHandler<Unit>()
            .httpRequest { userAPI.logout() }
            .sendRequest()
    }
}