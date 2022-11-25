package com.example.data.remote.datasource

import com.example.data.remote.network.FoundAPI
import com.example.data.remote.request.found.EditFoundRequest
import com.example.data.remote.request.found.FoundRequest
import com.example.data.util.HttpHandler
import javax.inject.Inject

class RemoteFoundDataSourceImpl @Inject constructor(
    private val foundAPI: FoundAPI
): RemoteFoundDataSource {
    override suspend fun registerFound(foundRequest: FoundRequest) {
        return HttpHandler<Unit>()
            .httpRequest { foundAPI.registerFound(foundRequest) }
            .sendRequest()
    }

    override suspend fun editFound(foundId: String, editFoundRequest: EditFoundRequest) {
        return HttpHandler<Unit>()
            .httpRequest { foundAPI.editFound(foundId, editFoundRequest) }
            .sendRequest()
    }

    override suspend fun deleteFound(foundId: String) {
        return HttpHandler<Unit>()
            .httpRequest { foundAPI.deleteFound(foundId) }
            .sendRequest()
    }
}