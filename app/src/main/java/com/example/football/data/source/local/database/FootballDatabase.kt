package com.example.football.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.football.data.source.local.dao.ClassementDao
import com.example.football.data.source.local.dao.ClubDao
import com.example.football.data.source.local.dao.MatchesDao
import com.example.football.data.source.local.entity.Classement
import com.example.football.data.source.local.entity.Club
import com.example.football.data.source.local.entity.Matches

@Database(entities = [Club::class, Matches::class, Classement::class], version = 1, exportSchema = false)
abstract class FootballDatabase : RoomDatabase() {
    abstract fun clubDao() : ClubDao
    abstract fun matchDao() : MatchesDao
    abstract fun classementDao() : ClassementDao
}