package com.example.domain.repository

import com.example.domain.entity.lost.FindAllEntity

interface LostRepository {
    suspend fun findAll(): List<FindAllEntity>
}