package com.example.domain.repository

import com.example.domain.entity.lost.LostEntity
import com.example.domain.param.lost.EditLostParam
import com.example.domain.param.lost.LostParam
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part
import okhttp3.RequestBody

interface LostRepository {
    suspend fun registerLost(lostParam: LostParam, file: Part)
    suspend fun editLost(lostId: String, editLostParam: EditLostParam, file: Part?)
    suspend fun deleteLost(lostId: String)
    suspend fun detailLost(lostId: String): LostEntity
    suspend fun findAll(): List<LostEntity>
    suspend fun findCategory(category: String?, address: String): List<LostEntity>
}