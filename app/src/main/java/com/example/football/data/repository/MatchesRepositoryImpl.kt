package com.example.football.data.repository

import com.example.football.data.source.local.FootballLocalDataSource
import com.example.football.data.source.local.entity.Matches
import com.example.football.domain.repository.MatchesRepository
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(private val footballLocal: FootballLocalDataSource) : MatchesRepository {
    override suspend fun insert(matches: Matches) {
        footballLocal.insertMatches(matches)
    }

}