package com.example.domain.repository

import com.example.domain.param.found.EditFoundParam
import com.example.domain.param.found.FoundParam

interface FoundRepository {
    suspend fun registerFound(foundParam: FoundParam)
    suspend fun editFound(foundId: String, editFoundParam: EditFoundParam)
}