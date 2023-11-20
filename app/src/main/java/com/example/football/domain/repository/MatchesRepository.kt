package com.example.football.domain.repository

import com.example.football.data.source.local.entity.Matches

interface MatchesRepository {
    suspend fun insert(matches: Matches)
}