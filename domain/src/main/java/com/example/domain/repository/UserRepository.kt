package com.example.domain.repository

import com.example.domain.entity.user.InfoEntity
import com.example.domain.entity.user.MyEntryEntity
import com.example.domain.param.user.EditInfoParam

interface UserRepository {
    suspend fun myLost(): List<MyEntryEntity>
    suspend fun myFound(): List<MyEntryEntity>
    suspend fun myInfo(): InfoEntity
    suspend fun editInfo(editInfoParam: EditInfoParam)
    suspend fun logout()
}