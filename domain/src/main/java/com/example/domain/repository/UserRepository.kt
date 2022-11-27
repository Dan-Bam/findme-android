package com.example.domain.repository

import com.example.domain.entity.user.MyEntryEntity

interface UserRepository {
    suspend fun myLost(): List<MyEntryEntity>
    suspend fun myFound(): List<MyEntryEntity>
}