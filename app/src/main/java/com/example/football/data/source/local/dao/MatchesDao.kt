package com.example.football.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.football.data.source.local.entity.Matches

@Dao
interface MatchesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(matches: Matches)
}