package com.example.domain.repository

import com.example.domain.entity.lost.LostEntity
import com.example.domain.param.lost.LostParam

interface LostRepository {
    suspend fun registerLost(lostParam: LostParam)
    suspend fun editLost(lostId: String, lostParam: LostParam)
    suspend fun deleteLost(lostId: String)
    suspend fun findAll(): List<LostEntity>
}