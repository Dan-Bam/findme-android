package com.example.data.remote.datasource

import com.example.data.remote.network.UserAPI
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val userAPI: UserAPI
): RemoteUserDataSource {
}