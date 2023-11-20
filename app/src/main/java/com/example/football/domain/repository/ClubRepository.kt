package com.example.football.domain.repository

import com.example.football.data.source.local.entity.Club
import kotlinx.coroutines.flow.Flow

interface ClubRepository {
    suspend fun insert(club: Club) : Long
    suspend fun fetchClub() : Flow<Array<String>>
    suspend fun fetchName() : Flow<List<String>>
    suspend fun id(name: String) : Int
    suspend fun name(id: Int) : String
}