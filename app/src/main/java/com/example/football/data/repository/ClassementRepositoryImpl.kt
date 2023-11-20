package com.example.football.data.repository

import com.example.football.data.source.local.FootballLocalDataSource
import com.example.football.data.source.local.entity.Classement
import com.example.football.domain.repository.ClassementRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClassementRepositoryImpl @Inject constructor(private val footballLocal: FootballLocalDataSource) : ClassementRepository {
    override suspend fun insert(classement: List<Classement>) {
        footballLocal.insertClassement(classement)
    }

    override suspend fun fetchClassement(): Flow<List<Classement>> {
        return footballLocal.fetchClassement()
    }

    override suspend fun getAllClubName(name: String): Classement? {
        return footballLocal.getAllClubName(name)
    }

    override suspend fun update(classement: Classement) {
        return footballLocal.update(classement)
    }

}