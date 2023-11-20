package com.example.football.domain.di

import android.content.Context
import androidx.room.Room
import com.example.football.data.source.local.FootballLocalDataSource
import com.example.football.data.source.local.dao.ClassementDao
import com.example.football.data.source.local.dao.ClubDao
import com.example.football.data.source.local.dao.MatchesDao
import com.example.football.data.source.local.database.FootballDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideFootballDatabase(@ApplicationContext context: Context) : FootballDatabase{
        return Room.databaseBuilder(
            context,
            FootballDatabase::class.java,
            "football.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideClubDao(database: FootballDatabase) : ClubDao = database.clubDao()

    @Singleton
    @Provides
    fun provideMatchesDao(database: FootballDatabase) : MatchesDao = database.matchDao()

    @Singleton
    @Provides
    fun provideClassementDao(database: FootballDatabase) : ClassementDao = database.classementDao()
}