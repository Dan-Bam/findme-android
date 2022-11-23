package com.example.domain.repository

import com.example.domain.entity.lost.LostEntity

interface LostRepository {
    suspend fun findAll(): List<LostEntity>
}