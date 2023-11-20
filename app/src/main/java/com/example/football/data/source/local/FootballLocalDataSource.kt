package com.example.football.data.source.local

import com.example.football.data.source.local.dao.ClassementDao
import com.example.football.data.source.local.dao.ClubDao
import com.example.football.data.source.local.dao.MatchesDao
import com.example.football.data.source.local.entity.Classement
import com.example.football.data.source.local.entity.Club
import com.example.football.data.source.local.entity.Matches
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FootballLocalDataSource @Inject constructor(
    private val clubDao: ClubDao,
    private val matchesDao: MatchesDao,
    private val classementDao: ClassementDao) {

    suspend fun insertClub(club: Club) : Long{
        return clubDao.insertClub(club)
    }

    suspend fun fetchClub() = flow { emit(clubDao.fetchClub()) }

    suspend fun idClub(name: String) = clubDao.getIdClubByName(name)
    suspend fun nameClub(id: Int) = clubDao.getNameClubById(id)
    suspend fun fetchName() = flow {emit(clubDao.fetchName())  }

    suspend fun insertMatches(matches: Matches){
        matchesDao.insert(matches)
    }

    suspend fun insertClassement(classement: List<Classement>){
        classementDao.insert(classement)
    }

    suspend fun fetchClassement() = flow {emit(classementDao.fetchClassement())  }
    suspend fun getAllClubName(name: String) = classementDao.getAllClubName(name)
    suspend fun update(classement: Classement) = classementDao.update(classement)
}