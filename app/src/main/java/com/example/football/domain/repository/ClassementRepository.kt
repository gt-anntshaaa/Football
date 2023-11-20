package com.example.football.domain.repository

import com.example.football.data.source.local.entity.Classement
import kotlinx.coroutines.flow.Flow

interface ClassementRepository {
    suspend fun insert(classement: List<Classement>)
    suspend fun fetchClassement() : Flow<List<Classement>>
    suspend fun getAllClubName(name: String) : Classement?
    suspend fun update(classement: Classement)
}