package com.example.domain.repository

import com.example.domain.param.param.FoundParam

interface FoundRepository {
    suspend fun registerFound(foundParam: FoundParam)
}