package com.example.football.data.repository

import com.example.football.data.source.local.FootballLocalDataSource
import com.example.football.data.source.local.entity.Club
import com.example.football.domain.repository.ClubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClubRepositoryImpl @Inject constructor(private val localFootball: FootballLocalDataSource) : ClubRepository {
    override suspend fun insert(club: Club) : Long {
        return localFootball.insertClub(club)
    }

    override suspend fun fetchClub(): Flow<Array<String>> {
        return localFootball.fetchClub()
    }

    override suspend fun fetchName(): Flow<List<String>> {
        return localFootball.fetchName()
    }

    override suspend fun id(name: String): Int {
        return localFootball.idClub(name)
    }

    override suspend fun name(id: Int): String {
        return localFootball.nameClub(id)
    }

}