package com.example.football.domain.di

import com.example.football.data.repository.ClassementRepositoryImpl
import com.example.football.data.repository.ClubRepositoryImpl
import com.example.football.data.repository.MatchesRepositoryImpl
import com.example.football.domain.repository.ClassementRepository
import com.example.football.domain.repository.ClubRepository
import com.example.football.domain.repository.MatchesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindClubRepository(clubRepositoryImpl: ClubRepositoryImpl) : ClubRepository

    @Singleton
    @Binds
    abstract fun bindMatchesRepository(matchRepositoryImpl: MatchesRepositoryImpl) : MatchesRepository

    @Singleton
    @Binds
    abstract fun bindClassementRepository(classementRepositoryImpl: ClassementRepositoryImpl) : ClassementRepository
}