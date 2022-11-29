package com.example.domain.repository

import com.example.domain.entity.user.InfoEntity
import com.example.domain.entity.user.MyFoundEntity
import com.example.domain.entity.user.MyLostEntity
import com.example.domain.param.user.EditInfoParam

interface UserRepository {
    suspend fun myLost(): List<MyLostEntity>
    suspend fun myFound(): List<MyFoundEntity>
    suspend fun myInfo(): InfoEntity
    suspend fun editInfo(editInfoParam: EditInfoParam)
    suspend fun logout()
}